import songService from "@/services/song-service.js";

export default {
    data() {
        return {
            recommendedSongs: [],
            otherSongs: [],
            newSong: {
                artist: "",
                title: "",
                duration: "",
            },
            songPlayingId: "",
        };
    },

    async created() {
        // get recommended songs
        this.songPlayingId = localStorage.getItem("songPlayingId") || "";
        const songsResponse = await songService.getUserFeed(
            localStorage.getItem("currentUser")
        );
        const songs = await songsResponse.json();
        this.recommendedSongs = songs.recommended;
        this.otherSongs = songs.others;
    },

    mounted() {
        addEventListener("song-started", (event) => {
            if (this.songPlayingId !== event.detail.songPlayingId)
                this.emitSongStoppedByOtherEvent();
            this.songPlayingId = event.detail.songPlayingId;
            localStorage.setItem("songPlayingId", this.songPlayingId);
        });
        addEventListener("song-stopped", (event) => {
            if (this.songPlayingId === event.detail.songStoppedId) {
                this.songPlayingId = "";
                localStorage.setItem("songPlayingId", this.songPlayingId);
            }
        });
    },

    methods: {
        emitSongStoppedByOtherEvent() {
            dispatchEvent(
                new CustomEvent("song-stopped-by-other", {
                    detail: {
                        songStoppedId: this.songPlayingId,
                    },
                })
            );
        },
        releaseSong() {
            this.handleRequest(() => {
                this.validateSong();
                this.tryReleaseSong();
                this.announce("Song has been released successfully.");
            });
        },
        validateSong() {
            if (
                !this.newSong.artist ||
                !this.newSong.title ||
                !this.newSong.duration
            ) {
                throw new Error(
                    "Please provide all the necessary data for new song."
                );
            }
        },
        async tryReleaseSong() {
            const response = await songService.releaseSong(this.newSong);
            if (response.status >= 400 && response.status < 600) {
                this.handle(
                    new Error(
                        "Song with given title and artist already released."
                    )
                );
                return;
            }
            setTimeout(() => location.reload(), 3000);
        },
    },
};
