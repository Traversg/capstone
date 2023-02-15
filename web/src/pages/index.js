import FiveLiftsClient from '../api/fiveLiftsClient';
import BindingClass from '../util/bindingClass';

/**
 * Logic needed for the create playlist page of the website.
 */
class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'redirectCurrentUser'], this);
    }

    /**
     * Add the header to the page and load the FiveLiftsClient.
     */
    mount() {
        this.client = new FiveLiftsClient();
        document.getElementById('login').addEventListener('click', this.client.login);
        this.redirectCurrentUser();
    }

    /**
     * Method to run user when mounted. Call the FiveLiftsService to see if the
     * user is already logged in.
     */
    async redirectCurrentUser() {
        const isCurrentUser = await this.client.getIsCurrentUser();

        if (isCurrentUser) {
            window.location.href = `/upcomingWorkouts.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);
