import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the bench press progress page of the website.
 */
class BarbellRowProgress extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'isLoggedIn', 'isCurrentUser', 'getWorkoutHistory'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the FiveLiftsClient.
     */
    async mount() {
        this.header.addHeaderToPage();
        this.client = new FiveLiftsClient();
        await this.isLoggedIn();
        await this.isCurrentUser();
    }

    /**
     * Method to check if a user is logged in.
     */
        async isLoggedIn() {
            const isLoggedIn = await this.client.isLoggedIn();
    
            if (!isLoggedIn) {
                window.location.href = `/index.html`;
            }
        }
    
    /**
     * Method to check if a user has a profile in the user table.
     */
    async isCurrentUser() {
        const currentUser =  await this.client.getIsCurrentUser();
        if (!currentUser) {
            const progressCard = document.getElementById('progressCard');
            progressCard.innerHTML = `
            <div id="currentWorkoutNonUserPopUp">
                <h1 class="notCurrentUser">Please enter your starting weights first.</h1>
                <button class="startingWeightsButton" id="startingWeightsButton" type="button">ENTER STARTING WEIGHTS</button>
            </div>
            `;
            document.getElementById('startingWeightsButton').addEventListener('click', this.redirectToStartingWeights);
        } else {
            this.getWorkoutHistory();
        }
    }

    /**
     * Method to get a user's workout history.
     */
    async getWorkoutHistory() {
        const workoutHistory = await this.client.getWorkoutHistory();
        this.dataStore.set('workoutHistory', workoutHistory);
        this.displayProgress();
    }

    /**
     * Method to display bench press progress.
     */
    displayProgress() {
        const workoutHistory = this.dataStore.get('workoutHistory');

        const progressTitle = document.getElementById('progressTitle');
        progressTitle.innerText = "BARBELL ROW";

        const progressCard = document.getElementById('progressCard');
        progressCard.classList.remove('hidden');

        for (let workout of workoutHistory) {
            const date = displayDate(workout.workoutDate);
            const weight = workout.barbellRowWeight;
            
            if (weight > 0) {
            const div = document.createElement('div');
            div.classList.add('data');
            div.innerHTML = `
            <h3 class="progressDate">${date}</h3>
            <h3 class="progressWeight">${weight}lbs</h3>
            `
            const progressCard = document.getElementById('progressCard');
            progressCard.appendChild(div);
        }        
        }
    }
}

function displayDate(date) {
    const year = date[0];
    const day = date[1];
    const month = date[2];

    return day + "." + month + "." + year;
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const barbellRowProgress = new BarbellRowProgress();
    barbellRowProgress.mount();
};

window.addEventListener('DOMContentLoaded', main);