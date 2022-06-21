import fetchProxy from "./fetch-proxy.js";

const httpProxy = fetchProxy("users");

export default {
    registerUser: async (newUser) =>
        httpProxy.executeRequest("", "POST", newUser),
    getUsers: async () => httpProxy.executeRequest("", "GET"),
};
