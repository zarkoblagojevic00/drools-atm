import fetchProxy from "./fetch-proxy.js";

const httpProxy = fetchProxy("songs");

export default {
    releaseSong: async (newSong) =>
        httpProxy.executeRequest("", "POST", newSong),
    getUserFeed: async (user) =>
        httpProxy.executeRequest(`feed/${user}`, "GET"),
    likeSong: async (songId, username) =>
        httpProxy.executeRequest(`${songId}/like/${username}`, "PUT"),
    playSong: async (songId, username) =>
        httpProxy.executeRequest(`${songId}/start/${username}`, "PUT"),
    stopSong: async (songId, username) =>
        httpProxy.executeRequest(`${songId}/end/${username}`, "PUT"),
};
