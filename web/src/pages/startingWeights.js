import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the starting weights page of the website.
 */
class StartingWeights extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'isLoggedIn', 'isCurrentUser'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the FiveListServiceClient.
     */
    async mount() {
        this.client = new FiveLiftsClient();
        await this.isLoggedIn();
        await this.isCurrentUser();
        document.getElementById('starting-weights-button').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
    }

    /**
     * Method to run when the create profile submit button is pressed. Call the FiveLiftsService to create the
     * profile.
     */
    async submit() {
        //evt.preventDefault();

        /* const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden'); */

        const createButton = document.getElementById('starting-weights-button');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const squat = document.getElementById('squat').value;
        const benchPress = document.getElementById('bench-press').value;
        const overheadPress = document.getElementById('overhead-press').value;
        const barbellRow = document.getElementById('barbell-row').value;
        const deadlift = document.getElementById('deadlift').value;
        const bodyWeight = document.getElementById('body-weight').value;

        const profile = await this.client.createProfile(squat, benchPress, overheadPress, 
            barbellRow, deadlift, bodyWeight, (error) => {
            console.log(error);
        });
        
        this.dataStore.set('profile', profile);
        this.redirectToWorkout();
    }

    /**
     * Checks to see if user is logged in. If not, user is redirected to home page.
     */
    async isLoggedIn() {
        const isLoggedIn = await this.client.isLoggedIn();

        if (!isLoggedIn) {
            window.location.href = `/index.html`;
        }
    }

    /**
     * Checks to see if user has already created a profile. 
     * If so, user is redirected to upcoming workouts page.
     */
    async isCurrentUser() {
        const currentUser = await this.client.getIsCurrentUser();
        if (currentUser.isCurrentUser) {
            window.location.href = `/upcomingWorkouts.html`
        } 
    }

    /**
     * When the profile is updated in the datastore, redirect to the upcoming workouts page.
     */
    redirectToWorkout() {
        const profile = this.dataStore.get('profile');
        if (profile != null) {
            window.location.href = `/upcomingWorkouts.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const startingWeights = new StartingWeights();
    startingWeights.mount();
};

window.addEventListener('DOMContentLoaded', main);