<template>
    <div>
        <div class="top-nav">
            <div class="logo-nav">
                <img
                    class="logo-img"
                    alt="logo"
                    src="@/assets/loudsound-icon.png"
                />
                <div class="app-name gradient-text">LoudSound</div>
            </div>
        </div>
        <div class="container whole-page">
            <div class="content">
                <div class="main-column main-column-left">
                    <div class="choose-and-register-user">
                        <div class="choose-user"></div>
                        <div class="register-user"></div>
                    </div>
                    <div class="release-song"></div>
                </div>
                <div class="main-column main-column-right">
                    <div class="recommended-songs"></div>
                    <div class="other-songs"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import swalToast from "@/mixins/swal-toast.js";

export default {
    mixins: [swalToast],
    data() {
        return {
            messages: [],
            newMessage: {
                content: "",
            },
        };
    },

    methods: {
        handle(error) {
            this.toast.fire({
                icon: "warning",
                title: error.message,
            });
        },
    },
};
</script>

<style>
.top-nav {
    display: flex;
    align-items: center;
    padding: 5px 5%;
    background: var(--background);
    border-bottom: 2px var(--primary-dark) solid;
    height: 60px;
}

.logo-nav {
    display: flex;
    align-items: center;
}

.logo-img {
    width: 64px;
    height: auto;
    margin-right: 15px;
}

.app-name {
    font-size: 1.8rem;
}

.container {
    background-image: -webkit-linear-gradient(
        -60deg,
        var(--primary-dark) 0%,
        var(--primary-comp-dark) 25%,
        var(--primary-dark) 64%,
        var(--primary-comp-dark) 90%
    );
    background-image: -moz-linear-gradient(
        -60deg,
        var(--primary-dark) 0%,
        var(--primary-comp-dark) 25%,
        var(--primary-dark) 64%,
        var(--primary-comp-dark) 90%
    );
    background-image: -ms-linear-gradient(
        -60deg,
        var(--primary-dark) 0%,
        var(--primary-comp-dark) 25%,
        var(--primary-dark) 64%,
        var(--primary-comp-dark) 90%
    );
    background-image: -o-linear-gradient(
        -60deg,
        var(--primary-dark) 0%,
        var(--primary-comp-dark) 25%,
        var(--primary-dark) 64%,
        var(--primary-comp-dark) 90%
    );
    background-image: linear-gradient(
        -60deg,
        var(--primary-dark) 0%,
        var(--primary-comp-dark) 25%,
        var(--primary-dark) 64%,
        var(--primary-comp-dark) 90%
    );
}

.content {
    height: 95%;
    margin: 1% 5%;
    border-radius: 0.7em;
    display: flex;
}

.main-column {
    margin: 5px;
    display: flex;
}

.main-column-left {
    flex: 60%;
}

.choose-and-register-user {
    flex: 45%;
    display: flex;
    flex-direction: column;
    margin-right: 10px;
}

.choose-user {
    flex: 45%;
    border: 3px solid red;
    margin-bottom: 8px;
}

.register-user {
    flex: 55%;
    border: 3px solid red;
}

.release-song {
    flex: 55%;
    border: 3px solid red;
}

.main-column-right {
    flex: 40%;
    display: flex;
    flex-direction: column;
}

.recommended-songs {
    flex: 45%;
    border: 3px solid red;
    margin-bottom: 8px;
}

.other-songs {
    flex: 55%;
    border: 3px solid red;
}

.control {
    background: transparent;
    padding: 0.75em 1em !important;
    color: var(--control-border-color);
}

.control:focus {
    border-color: var(--primary-comp);
}

::-webkit-scrollbar {
    width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
    background: var(--background-lighter);
    border-radius: 0.7em;
}

/* Handle */
::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 0.7em;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555;
}

@media screen and (min-device-width: 1200px) and (max-device-width: 1600px) and (-webkit-min-device-pixel-ratio: 1) {
    .top-nav {
        padding: 5px 10px;
    }

    .app-name {
        font-size: 1.65rem;
    }

    .logo-img {
        width: 58px;
        margin-right: 10px;
    }

    .content {
        height: 100%;
        margin: 0;
        border-radius: 0;
        border: 0;
    }
}
</style>
