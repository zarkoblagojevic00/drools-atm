<template>
    <div
        class="song-expo-container transition-ease-in"
        :class="{ 'song-expo-container-playing': isPlaying }"
    >
        <div class="song-title-stats">
            <div class="song-title">
                {{ songExpo.song.artist }} - {{ songExpo.song.title }}
            </div>
            <div class="song-stats">
                <div class="song-stats-img-num">
                    <button
                        @click="likeAction.execute"
                        class="song-stats-like-song"
                    >
                        <img
                            class="song-stats-icon-likes"
                            :class="{ 'song-stats-not-liked ': !isLiked }"
                            :src="likeAction.imgSrc"
                            alt="Plays"
                        />
                    </button>
                    <div>{{ songExpo.song.likesNumber }}</div>
                </div>
                <div class="song-stats-img-num">
                    <img
                        class="song-stats-icon"
                        src="@/assets/play-icon.png"
                        alt="Plays"
                    />
                    <div>{{ songExpo.song.timesListenedNumber }}</div>
                </div>
                <div class="song-stats-img-num">
                    <img
                        class="song-stats-icon"
                        src="@/assets/stop-icon.png"
                        alt="Skips"
                    />
                    <div>{{ songExpo.song.timesSkippedNumber }}</div>
                </div>
                <div class="song-stats-status">
                    <div class="song-stats-status-info">
                        {{ songExpo.song.status }}
                    </div>
                </div>
            </div>
        </div>
        <div>
            <button
                @click="listenAction.execute"
                :class="
                    !isPlaying ? 'song-interact-play' : 'song-interact-stop'
                "
                class="song-interact-img-container transition-ease-in"
            >
                <img
                    class="song-interact-img"
                    :src="listenAction.imgSrc"
                    alt=""
                />
            </button>
        </div>
    </div>
</template>

<script>
import songService from "@/services/song-service.js";

export default {
    props: {
        songExpo: Object,
        isPlaying: Boolean,
    },

    data() {
        return {
            currentUser: localStorage.getItem("currentUser"),
            likesMap: {},
            isLiked: false,
        };
    },
    created() {
        this.likesMap = JSON.parse(localStorage.getItem("likesMap"))[
            this.currentUser
        ];
        this.isLiked = this.likesMap[this.songExpo.song.id];
    },
    computed: {
        likeAction() {
            return this.isLiked
                ? {
                      execute: this.alreadyLiked,
                      imgSrc: getImgUrl("heart-filled-icon"),
                  }
                : {
                      execute: this.likeSong,
                      imgSrc: getImgUrl("heart-outline-icon"),
                  };
        },
        listenAction() {
            return this.isPlaying
                ? {
                      execute: this.stopSong,
                      imgSrc: getImgUrl("stop-icon"),
                  }
                : {
                      execute: this.playSong,
                      imgSrc: getImgUrl("play-icon"),
                  };
        },
    },

    mounted() {
        addEventListener("song-stopped-by-other", (event) => {
            if (this.songExpo.song.id === event.detail.songStoppedId) {
                this.stopSong();
            }
        });
    },

    methods: {
        playSong() {
            console.log(`Song ${this.songExpo.song.id} is playing.`);
            songService.playSong(
                this.songExpo.song.id,
                localStorage.getItem("currentUser")
            );
            this.emitSongStartedEvent();
        },
        emitSongStartedEvent() {
            dispatchEvent(
                new CustomEvent("song-started", {
                    detail: {
                        songPlayingId: this.songExpo.song.id,
                    },
                })
            );
        },
        async stopSong() {
            console.log(`Song ${this.songExpo.song.id} is stopped.`);
            await songService.stopSong(
                this.songExpo.song.id,
                localStorage.getItem("currentUser")
            );
            this.emitSongStoppedEvent();
            location.reload();
        },
        emitSongStoppedEvent() {
            dispatchEvent(
                new CustomEvent("song-stopped", {
                    detail: {
                        songStoppedId: this.songExpo.song.id,
                    },
                })
            );
        },

        async likeSong() {
            await songService.likeSong(
                this.songExpo.song.id,
                localStorage.getItem("currentUser")
            );
            this.isLiked = true;
            this.likesMap[this.songExpo.song.id] = true;
            let globalLikesMap = JSON.parse(localStorage.getItem("likesMap"));
            globalLikesMap[this.currentUser] = this.likesMap;
            localStorage.setItem("likesMap", JSON.stringify(globalLikesMap));
            location.reload();
        },
        alreadyLiked() {},
    },
};

const getImgUrl = (pictureName) => {
    const images = require.context("../assets/", false, /\.png$/);
    return images("./" + pictureName + ".png");
};
</script>

<style scoped>
.song-expo-container {
    min-height: 70px;
    border: 2px solid var(--background-lighter);
    border-radius: 0.5em;
    padding: 0 0.5em;
    margin-top: 10px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    color: var(--control-border-color-focused);
}

.song-expo-container-playing {
    border: 2px solid var(--primary-comp);
}

.song-title-stats {
    width: 80%;
    min-height: 50px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 8px 0;
}

.song-title {
    display: flex;
    align-items: flex-start;
    font-size: 1.05rem;
    border-bottom: 1px solid var(--background-lighter);
    margin-bottom: 0.25em;
    padding-bottom: 0.25em;
}

.song-stats {
    min-height: 40px;
    display: flex;
    justify-content: space-between;
}

.song-stats-img-num {
    flex: 15%;
    display: flex;
    align-items: center;
    padding: 0.3 0.5rem;
    /* border: 2px solid blue; */
}

.song-stats-icon {
    height: 24px;
    width: auto;
    margin-right: 12px;
    margin-left: 10px;
}

.song-stats-like-song {
    background: transparent;
    outline: none;
    border: none;
}

.song-stats-icon-likes {
    height: 30px;
    width: auto;
    margin-right: 8px;
}

.song-stats-not-liked {
    cursor: pointer;
}

.song-stats-status {
    flex: 40%;
    display: flex;
    align-items: center;
}

.song-stats-status-info {
    text-transform: lowercase;
    min-width: 50px;
    font-size: 0.95rem;
    padding: 0.2em 0.3em;
    border-radius: 0.35em;
    border: 2px solid var(--primary-comp);
    background: var(--background-lighter);
}

.song-interact-img-container {
    color: transparent;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    border: 2px solid var(--background-lighter);
    cursor: pointer;
    outline: none;
    background: transparent;
    display: flex;
    justify-content: center;
    align-items: center;
}

.song-interact-img {
    height: 28px;
    width: auto;
}

.song-interact-play {
    padding-left: 12px;
}

.song-interact-stop {
    border: 3px solid var(--primary-comp);
    padding: 7px;
}
</style>
