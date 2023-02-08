import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the upcoming workouts page of the website.
 */
class CurrentWorkout extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'displayCurrentWorkout'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        this.header.addHeaderToPage();
        this.client = new FiveLiftsClient();
        this.displayCurrentWorkout();
    }

    /**
     * Method to run when mounted. Call the FiveLiftsService to get the
     * current workout.
     */
    async displayCurrentWorkout() {
        const currentWorkout = await this.client.getCurrentWorkout();
        const currentWorkoutType = currentWorkout[0].workoutType;
        if (currentWorkoutType == 'WORKOUT_A') {
            displayWorkoutA(currentWorkout[0]);
        } else {
            displayWorkoutB(currentWorkout[0]);
        }
    }
}

function displayWorkoutA(workoutA) {
    let workoutCard = document.getElementById("currentWorkoutCard");
    workoutCard.innerHTML = `     
    <div class="squat">
        <h3>Squat</h3>
        <div class="weight">
            <h3>5x5</h3>
            <h3 class="setWeight">${workoutA.squatWeight}lbs</h3>
        </div>
    </div>
    <div class="squatReps">
        <form class="squatRepsFormField>
            <input type="number" required class="validated-field id="squatSet1" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet2" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet3" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet4" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet5" min="1" max="5" placeholder="5" 
            autofocus>
        </form>
    </div>
    <div class="benchPress">
        <h3>Bench Press</h3>
        <div class="weight">
            <h3>5x5</h3>
            <h3 class="setWeight">${workoutA.benchPressWeight}lbs</h3>
        </div>
    </div>
    <div class="benchPressReps">
        <form class="benchPressRepsFormField>
            <input type="number" required class="validated-field id="benchPressSet1" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="benchPressSet2" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="benchPressSet3" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="benchPressSet4" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="benchPressSet5" min="1" max="5" placeholder="5" 
            autofocus>
        </form>
    </div>
    <div class="barbellRowPress">
        <h3>Barbell Row</h3>
        <div class="weight">
            <h3>5x5</h3>
            <h3 class="setWeight">${workoutA.barbellRowWeight}lbs</h3>
        </div>
    </div>
    <div class="barbellRowReps">
    <form class="barbellRowRepsFormField>
        <input type="number" required class="validated-field id="barbellRowSet1" min="1" max="5" placeholder="5" 
        autofocus>
        <input type="number" required class="validated-field id="barbellRowSet2" min="1" max="5" placeholder="5" 
        autofocus>
        <input type="number" required class="validated-field id="barbellRowSet3" min="1" max="5" placeholder="5" 
        autofocus>
        <input type="number" required class="validated-field id="barbellRowSet4" min="1" max="5" placeholder="5" 
        autofocus>
        <input type="number" required class="validated-field id="barbellRowSet5" min="1" max="5" placeholder="5" 
        autofocus>
    </form>
    <form class="bodyWeight">
        <input type="number" required class="validated-field" id="body-weight" min="0" placeholder="0.0 lbs"
        autofocus>
    </form>
</div>`
}

function displayWorkoutB(workoutB) {
    let workoutCard = document.getElementById("currentWorkoutCard");
    workoutCard.innerHTML = `     
    <div class="squat">
        <h3>Squat</h3>
        <div class="weight">
            <h3>5x5</h3>
            <h3 class="setWeight">${workoutB.squatWeight}lbs</h3>
        </div>
    </div>
    <div class="squatReps">
        <form class="squatRepsFormField>
            <input type="number" required class="validated-field id="squatSet1" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet2" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet3" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet4" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="squatSet5" min="1" max="5" placeholder="5" 
            autofocus>
        </form>
    </div>
    <div class="overheadPress">
        <h3>Overhead Press</h3>
        <div class="weight">
            <h3>5x5</h3>
            <h3 class="setWeight">${workoutB.overheadPressWeight}lbs</h3>
        </div>
    </div>
    <div class="overheadPressReps">
        <form class="overheadPressRepsFormField>
            <input type="number" required class="validated-field id="overheadPressSet1" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="overheadPressSet2" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="overheadPressSet3" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="overheadPressSet4" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field id="overheadPressSet5" min="1" max="5" placeholder="5" 
            autofocus>
        </form>
    </div>
    <div class="deadlift">
        <h3>Deadlift</h3>
        <div class="weight">
            <h3>1x5</h3>
            <h3 class="setWeight">${workoutB.deadliftWeight}lbs</h3>
        </div>
    </div>
    <div class="deadliftReps">
    <form class="deadliftRepsFormField>
        <input type="number" required class="validated-field id="deadliftSet1" min="1" max="5" placeholder="5" 
        autofocus>
    </form>
    <form class="bodyWeight">
        <input type="number" required class="validated-field" id="body-weight" min="0" placeholder="0.0 lbs"
        autofocus>
    </form>
</div>`
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const currentWorkout = new CurrentWorkout();
    currentWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);
