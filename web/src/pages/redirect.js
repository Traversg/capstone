import FiveLiftsClient from '../api/fiveLiftsClient';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the redirect page of the website.
 */
class Redirect extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'redirectToRelevantPage'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Load the FiveLifts Client.
     */
    mount() {
        this.client = new FiveLiftsClient();
        this.redirectToRelevantPage();
    }

    /**
     * Take user to starting weights page or upcoming workouts page.
     */
    async redirectToRelevantPage() {
        const isCurrentUser = await this.client.getIsCurrentUser();

        if (isCurrentUser) {
            window.location.href = `/upcomingWorkouts.html`;
        } else {
            window.location.href = `/startingWeights.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const redirect = new Redirect();
    redirect.mount();
};

window.addEventListener('DOMContentLoaded', main);