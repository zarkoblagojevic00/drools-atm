import userService from "@/services/user-service.js";

export default {
    data() {
        return {
            currentUser: "",
            users: [],
            newUser: {
                username: "",
                genre: "ROCK",
            },
            genres: ["ROCK", "JAZZ", "BLUES", "METAL", "COUNTRY", "RAP"],
            likesMap: {},
        };
    },
    async created() {
        // get all users
        const usersResponse = await userService.getUsers();
        this.users = await usersResponse.json();
        this.currentUser =
            localStorage.getItem("currentUser") || this.users[0].username;
        this.newSong.artist = this.currentUser;

        this.likesMap =
            JSON.parse(localStorage.getItem("likesMap")) ||
            localStorage.setItem(
                "likesMap",
                JSON.stringify(
                    this.users.reduce((prev, curr) => {
                        prev[curr.username] = {};
                        return prev;
                    }, {})
                )
            );
    },
    methods: {
        switchUser(username) {
            this.currentUser = username;
            localStorage.setItem("currentUser", username);
            this.newSong.artist = username;
            location.reload();
        },
        registerUser() {
            this.handleRequest(() => {
                this.validateUser();
                this.tryRegisterUser();
                this.updateLikesMap();
                this.announce("User has been registered successfully.");
            });
        },
        validateUser() {
            if (!this.newUser.username) {
                throw new Error("Please provide a username for new user.");
            }
        },
        async tryRegisterUser() {
            const response = await userService.registerUser(this.newUser);
            if (response.status >= 400 && response.status < 600) {
                this.handle(
                    new Error("User with given username already exists.")
                );
                return;
            }
            const registeredUser = await response.json();
            this.users.push(registeredUser);
        },
        updateLikesMap() {
            this.likesMap[this.newUser.username] = {};
            localStorage.setItem("likesMap", JSON.stringify(this.likesMap));
        },
    },
};
