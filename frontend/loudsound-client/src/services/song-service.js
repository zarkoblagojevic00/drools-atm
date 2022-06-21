import fetchProxy from "./fetch-proxy.js";

const httpProxy = fetchProxy("songs");

export default {
    releaseSong: async (newSong) =>
        httpProxy.executeRequest("", "POST", newSong),
    getUserFeed: async (user) =>
        httpProxy.executeRequest(`feed/${user}`, "GET"),
};
