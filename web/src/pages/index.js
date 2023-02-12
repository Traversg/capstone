import FiveLiftsClient from '../api/fiveLiftsClient';
import BindingClass from '../util/bindingClass';

/**
 * Logic needed for the create playlist page of the website.
 */
class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'login', 'redirectLoggedInUser'], this);
    }

    /**
     * Add the header to the page and load the FiveLiftsClient.
     */
    mount() {
        document.getElementById('login').addEventListener('click', this.login);
        this.client = new FiveLiftsClient();
        //this.redirectLoggedInUser();
    }

    /**
     * Method to run when the login button is pressed. Call the FiveLiftsService to login
     * the user.
     */
    async login() {
        document.getElementById('login').addEventListener('click', this.client.login);
    }

    /**
     * Method to run user when mounted. Call the FiveLiftsService to see if the
     * user is already logged in.
     */
    async redirectLoggedInUser() {
        const isLoggedIn = this.client.isLoggedIn();

        if (isLoggedIn) {
            window.location.href = `/upcomingWorkouts.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const login = new Index();
    login.mount();
};

window.addEventListener('DOMContentLoaded', main);
