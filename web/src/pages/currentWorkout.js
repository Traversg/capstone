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
        this.bindClassMethods(['mount', 'displayCurrentWorkout', 'finishWorkout', 'finishWorkoutA',
            'finishWorkoutB', 'startTimer'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('workoutSubmitButton').addEventListener('click', this.finishWorkout);
        document.getElementById('timerButton').addEventListener('click', this.startTimer);
        this.header.addHeaderToPage();
        this.client = new FiveLiftsClient();
        this.displayCurrentWorkout();
        const startTime = new Date();
        this.dataStore.set('startTime', startTime);
    }

    /**
     * Method to run when mounted. Call the FiveLiftsService to get the
     * current workout.
     */
    async displayCurrentWorkout() {
        const currentWorkout = await this.client.getCurrentWorkout();
        this.dataStore.set('currentWorkout', currentWorkout[0]);
        const currentWorkoutType = currentWorkout[0].workoutType;
        this.dataStore.set('currentWorkoutType', currentWorkoutType);
        let workoutTitle = document.getElementById('currentWorkoutTitle');
        if (currentWorkoutType == 'WORKOUT_A') {
            workoutTitle.innerText = 'WORKOUT A';
            displayWorkoutA(currentWorkout[0]);
        } else {
            workoutTitle.innerText = 'WORKOUT B';
            displayWorkoutB(currentWorkout[0]);
        }
    }

    /**
     * Method to run when user clicks finish workout button. Call finishWorkoutA or finishWorkoutB 
     * to add the current workout.
     */
    async finishWorkout() {
        const finshTime = new Date();
        this.dataStore.set('finishTime', finshTime);

        const submitButton = document.getElementById('workoutSubmitButton');
        submitButton.innerText = "Adding Workout...";

        const workoutType = this.dataStore.get('currentWorkoutType');
        if (workoutType == 'WORKOUT_A') {
            const completedWorkout = this.finishWorkoutA();
            this.dataStore.set('completedWorkout', completedWorkout);
        } else {
            const completedWorkout = this.finishWorkoutB();
            this.dataStore.set('completedWorkout', completedWorkout);
        }

        this.redirectToUpcomingWorkouts();
    }

    /**
     * Method to run when called in the finish workout method. Call the FiveLiftsService to add the
     * current workout.
     */
    async finishWorkoutA() {
        const currentWorkout = this.dataStore.get('currentWorkout');
        const date = getDateString();
        const workoutType = "Workout A";
        const timeStarted = getTimeString(this.dataStore.get('startTime'));
        const timeEnded = getTimeString(this.dataStore.get('finishTime'));
        const squatWeight = currentWorkout.squatWeight;
        const benchPressWeight = currentWorkout.benchPressWeight;
        const barbellRowWeight = currentWorkout.barbellRowWeight;
        const overheadPressWeight = 0;
        const deadliftWeight = 0;
        const squatReps = getSquatReps();
        const benchPressReps = getBenchPressReps();
        const barbellRowReps = getBarbellRowReps();
        const overheadPressReps = [];
        const deadliftReps = [];
        const bodyWeight = document.getElementById('body-weight').value;
    
        const workout = await this.client.addWorkout(date, workoutType, timeStarted,
            timeEnded, squatWeight, benchPressWeight, overheadPressWeight, barbellRowWeight,
            deadliftWeight, squatReps, benchPressReps, overheadPressReps,
            barbellRowReps, deadliftReps, bodyWeight, (error) => {
                const errorBlock = document.getElementById('errorMessage');
                errorBlock.innerText = `Error ${error.message}`;
        });
    }

    /**
     * Method to run when called in the finish workout method. Call the FiveLiftsService to add the
     * current workout.
     */
    async finishWorkoutB() {
        const currentWorkout = this.dataStore.get('currentWorkout');
        const date = getDateString();
        const workoutType = "Workout B";
        const timeStarted = getTimeString(this.dataStore.get('startTime'));
        const timeEnded = getTimeString(this.dataStore.get('finishTime'));
        const squatWeight = currentWorkout.squatWeight;
        const benchPressWeight = 0;
        const barbellRowWeight = 0;
        const overheadPressWeight = currentWorkout.overheadPressWeight;
        const deadliftWeight = currentWorkout.deadliftWeight;
        const squatReps = getSquatReps();
        const benchPressReps = [];
        const barbellRowReps = [];
        const overheadPressReps = getOverheadPressReps();
        const deadliftReps = getDeadliftReps();
        const bodyWeight = document.getElementById('body-weight').value;
    
        const workout = await this.client.addWorkout(date, workoutType, timeStarted,
            timeEnded, squatWeight, benchPressWeight, overheadPressWeight, barbellRowWeight,
            deadliftWeight, squatReps, benchPressReps, overheadPressReps,
            barbellRowReps, deadliftReps, bodyWeight, (error) => {
                const errorBlock = document.getElementById('errorMessage');
                errorBlock.innerText = `Error ${error.message}`;
        });
    }

    async redirectToUpcomingWorkouts() {
        const completedWorkout = this.dataStore.get('completedWorkout');
        if (completedWorkout != null) {
            window.location.href = `/upcomingWorkouts.html`;
        }
    }

    async startTimer() {
        const timerButton = document.getElementById('timerButton');
        timerButton.disabled = true;
        timerButton.style.backgroundColor = "rgb(255,0,36,0.5)";
        let second = 0;
        const timerBlock = document.getElementById('timerDisplay');
        let timer = setInterval(function() {
            timerBlock.classList.remove('hidden');
            document.getElementById('timerSeconds').innerHTML = upTimer(++second % 60);
            document.getElementById('timerMinutes').innerHTML = upTimer(parseInt(second / 60, 10));
            if (second === 180) {
                clearInterval(timer);
                timerButton.disabled = false;
                timerButton.style.backgroundColor = "rgb(255,0,36)";
                timerBlock.classList.add('hidden');
            }
        }, 1000);
    }
}

function displayWorkoutA(workoutA) {
    let workoutCard = document.getElementById("currentWorkoutCard");
    workoutCard.innerHTML = `
    <form class="current-workout-form" id="current-workout-form">     
        <div class="exercise">
            <h3>Squat</h3>
            <div class="weight">
                <h3>5x5</h3>
                <h3 class="setWeight">${workoutA.squatWeight}lbs</h3>
            </div>
        </div>
        <div class="reps">
            <div class="repInputs">
                <input type="number" required class="validated-field" id="squatSet1" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet2" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet3" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet4" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet5" min="1" max="5" placeholder="5" 
                autofocus>
            </div>
        </div>
        <div class="exercise">
            <h3>Bench Press</h3>
            <div class="weight">
                <h3>5x5</h3>
                <h3 class="setWeight">${workoutA.benchPressWeight}lbs</h3>
            </div>
        </div>
        <div class="reps">
            <div class="repInputs">
                <input type="number" required class="validated-field" id="benchPressSet1" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet2" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet3" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet4" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet5" min="1" max="5" placeholder="5" 
                autofocus>
            </div>
        </div>
        <div class="exercise">
            <h3>Barbell Row</h3>
            <div class="weight">
                <h3>5x5</h3>
                <h3 class="setWeight">${workoutA.barbellRowWeight}lbs</h3>
            </div>
        </div>
        <div class="reps">
        <div class="repInputs">
            <input type="number" required class="validated-field" id="barbellRowSet1" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet2" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet3" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet4" min="1" max="5" placeholder="5" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet5" min="1" max="5" placeholder="5" 
            autofocus>
        </div>
        <div class="bodyWeight">
            <h3>Body Weight</h3>
            <input type="number" required class="validated-field" id="body-weight" min="0" placeholder="0.0 lbs"
            autofocus>
        </div>
    </form>
</div>`
}

function displayWorkoutB(workoutB) {
    let workoutCard = document.getElementById("currentWorkoutCard");
    workoutCard.innerHTML = `
    <form class="current-workout-form" id="current-workout-form">      
        <div class="exercise">
            <h3>Squat</h3>
            <div class="weight">
                <h3>5x5</h3>
                <h3 class="setWeight">${workoutB.squatWeight}lbs</h3>
            </div>
        </div>
        <div class="reps">
            <div class="repInputs">
                <input type="number" required class="validated-field" id="squatSet1" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet2" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet3" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet4" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet5" min="1" max="5" placeholder="5" 
                autofocus>
            </div>
        </div>
        <div class="exercise">
            <h3>Overhead Press</h3>
            <div class="weight">
                <h3>5x5</h3>
                <h3 class="setWeight">${workoutB.overheadPressWeight}lbs</h3>
            </div>
        </div>
        <div class="reps">
            <div class="repInputs">
                <input type="number" required class="validated-field" id="overheadPressSet1" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet2" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet3" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet4" min="1" max="5" placeholder="5" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet5" min="1" max="5" placeholder="5" 
                autofocus>
            </div>
        </div>
        <div class="exercise">
            <h3>Deadlift</h3>
            <div class="weight">
                <h3>1x5</h3>
                <h3 class="setWeight">${workoutB.deadliftWeight}lbs</h3>
            </div>
        </div>
        <div class="reps">
        <div class="repInputs">
            <input type="number" required class="validated-field" id="deadliftSet1" min="1" max="5" placeholder="5" 
            autofocus>
        </div>
        <div class="bodyWeight">
            <h3>Body Weight</h3>
            <input type="number" required class="validated-field" id="body-weight" min="0" placeholder="0.0 lbs"
            autofocus>
    </div>
    </form>
</div>`;
}

function upTimer(count) {
    return count > 9 ? count : "0" + count;
}

function getDateString() {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth();
    const date = currentDate.getDate();
    
    if (date < 10) {
        return `${year}-0${Number(month) + 1}-0${date}`;
    } else {
        return `${year}-0${Number(month) + 1}-${date}`
    }
}

function getTimeString(time) {
    const year = time.getFullYear();
    const month = time.getMonth();
    const date = time.getDate();
    const hour = time.getHours();
    const minute = time.getMinutes();
    const second = time.getSeconds();
    const milliseconds = time.getMilliseconds();

    if (date < 10) {
        return `${year}-0${Number(month) + 1}-0${date}T${hour}:${minute}:${second}.${milliseconds}`
    } else {
        return `${year}-0${Number(month) + 1}-${date}T${hour}:${minute}:${second}.${milliseconds}`;
    }
}

function getSquatReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "squatSet" + (i + 1);
        let testElement = document.getElementById('body-weight');
        let setNumberElement = document.getElementById(setNumber);
        reps[i] = document.getElementById(setNumber).value;
    }

    return reps;
}

function getBenchPressReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "benchPressSet" + (i + 1);
        reps[i] = document.getElementById(setNumber).value;
    }

    return reps;
}

function getBarbellRowReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "barbellRowSet" + (i + 1);
        reps[i] = document.getElementById(setNumber).value;
    }

    return reps;
}

function getOverheadPressReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "overheadPressSet" + (i + 1);
        reps[i] = document.getElementById(setNumber).value;
    }

    return reps;
}

function getDeadliftReps() {
    const reps = [];

    reps[0] = document.getElementById('deadliftSet1').value;

    return reps;
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const currentWorkout = new CurrentWorkout();
    currentWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);
