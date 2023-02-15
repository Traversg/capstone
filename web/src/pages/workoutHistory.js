import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the workout history page of the website.
 */
class WorkoutHistory extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'displayWorkoutHistory', 'isLoggedIn', 
        'isCurrentUser', 'resetProfile'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    async mount() {
        document.getElementById('resetButton').addEventListener('click', this.resetProfile);
        this.header.addHeaderToPage();
        this.client = new FiveLiftsClient();
        await this.isLoggedIn();
        await this.isCurrentUser();
        
    }

    /**
     * Method to run when mounted. Call the FiveLiftsService to get the
     * workout history.
     */
    async displayWorkoutHistory() {
        const workoutHistory = await this.client.getWorkoutHistory();

        for (let workout in workoutHistory) {
            let currentWorkout = workoutHistory[workout];
            if (currentWorkout.workoutType == 'WORKOUT_A') {
                displayWorkoutA(currentWorkout);
            } else {
                displayWorkoutB(currentWorkout);
            }
        }
    }

    async isLoggedIn() {
        const isLoggedIn = await this.client.isLoggedIn();

        if (!isLoggedIn) {
            window.location.href = `/index.html`;
        }
    }

    async isCurrentUser() {
        const currentUser =  await this.client.getIsCurrentUser();
        if (!currentUser) {
            const workoutHistoryCard = document.getElementById('workoutHistoryCard');
            workoutHistoryCard.innerHTML = `
            <h1 class="notCurrentUser">Please enter your starting weights first.</h1>
            <button class="startingWeightsButton" id="startingWeightsButton" type="button">ENTER STARTING WEIGHTS</button>
            `;
            document.getElementById('startingWeightsButton').addEventListener('click', this.redirectToStartingWeights);
        } else {
            this.displayWorkoutHistory();
        }
    }

    async resetProfile() {
        const isDeleted = await this.client.deleteUserProfileAndWorkoutHistory();
        if (isDeleted.deleted) {
            window.location.href = `/index.html`;
        }
    }

    redirectToStartingWeights() {
        window.location.href = `/startingWeights.html`;
    }
}

/**
 * Helper function to display date in worded format.
 * @param {*} year 
 * @param {*} month 
 * @param {*} day 
 * @returns worded format
 */
function getDisplayDate(year, month, day) {
        const workoutDate = new Date(year, month, day);
        const dayOfWeekName = workoutDate.toLocaleDateString('default', {
            weekday: 'long'
        });
        const monthName = workoutDate.toLocaleDateString(`default`, {
            month: 'long'
        });
        // console.log(monthName);
        return `${dayOfWeekName}, ${monthName} ${day}`
}

/**
 * Helper function to display Workout A.
 * @param {*} workoutA 
 * @param {*} workoutNumber 
 */
function displayWorkoutA(workoutA) {
    let displayDate = getDisplayDate(workoutA.workoutDate[0],
        (workoutA.workoutDate[1] - 1), workoutA.workoutDate[2]);
    const squatReps = getSquatRepsDisplay(workoutA);
    const benchPressReps = getBenchPressRepsDisplay(workoutA);
    const barbellRowReps = getBarbellRowRepsDispay(workoutA);

    let workoutCard = document.getElementById('workoutHistory');
    let workoutBlock = document.createElement('div');
    workoutBlock.classList.add('workout');
    workoutBlock.innerHTML = `   
        <div class="workoutTypeAndDate">
            <p class="type">Workout A</p>
            <p class="date">${displayDate}</p> 
        </div>
        <div class="exercise">
            <h3>Squat</h3>
            <div class="weight">
                <h3>${squatReps}</h3>
                <h3 class="setWeight">${workoutA.squatWeight}lbs</h3>
            </div>
        </div>
        <hr>
        <div class="exercise">
            <h3>Bench Press</h3>
            <div class="weight">
                <h3>${benchPressReps}</h3>
                <h3 class="setWeight">${workoutA.benchPressWeight}lbs</h3>
            </div>
        </div>
        <hr>
        <div class="exercise">
            <h3>Barbell Row</h3>
            <div class="weight">
                <h3>${barbellRowReps}</h3>
                <h3 class="setWeight">${workoutA.barbellRowWeight}lbs</h3>
            </div>
        </div>
        <hr>
        <div class="bodyWeightHistory">
            <h3>Body Weight</h3>
            <h3 class="setWeight">${workoutA.bodyWeight}lbs</h3>
        </div>`;
    workoutCard.appendChild(workoutBlock);
}

/**
 * Helper function to display Workout B.
 * @param {*} workoutA 
 * @param {*} workoutNumber 
 */
function displayWorkoutB(workoutB) {
    let displayDate = getDisplayDate(workoutB.workoutDate[0],
        (workoutB.workoutDate[1] - 1), workoutB.workoutDate[2]);
    const squatReps = getSquatRepsDisplay(workoutB);
    const overheadPressReps = getOverheadPressRepsDispay(workoutB);
    const deadliftReps = getDeadliftRepsDispay(workoutB);
        
    let workoutCard = document.getElementById('workoutHistory');
    let workoutBlock = document.createElement('div');
    workoutBlock.classList.add('workout');
    workoutBlock.innerHTML = `    
        <div class="workoutTypeAndDate">
            <p class="type">Workout B</p>
            <p class="date">${displayDate}</p> 
        </div>
        <div class="exercise">
            <h3>Squat</h3>
            <div class="weight">
                <h3>${squatReps}</h3>
                <h3 class="setWeight">${workoutB.squatWeight}lbs</h3>
            </div>
        </div>
        <hr>
        <div class="exercise">
            <h3>Overhead Press</h3>
            <div class="weight">
                <h3>${overheadPressReps}</h3>
                <h3 class="setWeight">${workoutB.overheadPressWeight}lbs</h3>
            </div>
        </div>
        <hr>
        <div class="exercise">
            <h3>${deadliftReps}</h3>
            <div class="weight">
                <h3>1x5</h3>
                <h3 class="setWeight">${workoutB.deadliftWeight}lbs</h3>
            </div>
        </div>
        <hr>
        <div class="bodyWeightHistory">
            <h3>Body Weight</h3>
            <h3 class="setWeight">${workoutB.bodyWeight}lbs</h3>
        </div>`;
    workoutCard.appendChild(workoutBlock);
}

function getSquatRepsDisplay(workout) {
    const squatReps = workout.squatReps;
    const maxReps = 25;
    let totalReps = 0;
    let repString = "";
    
    for (let rep of squatReps) {
        repString += rep + " | ";
        totalReps += rep;
    }

    if (totalReps === maxReps) {
        return "5x5";
    }

    return repString.slice(0, repString.length - 2);
}

function getBenchPressRepsDisplay(workout) {
    const benchPressReps = workout.benchPressReps;
    const maxReps = 25;
    let totalReps = 0;
    let repString = "";
    
    for (let rep of benchPressReps) {
        repString += rep + " | ";
        totalReps += rep;
    }

    if (totalReps === maxReps) {
        return "5x5";
    }

    return repString.slice(0, repString.length - 2);
}

function getBarbellRowRepsDispay(workout) {
    const barbellRowReps = workout.barbellRowReps;
    const maxReps = 25;
    let totalReps = 0;
    let repString = "";
    
    for (let rep of barbellRowReps) {
        repString += rep + " | ";
        totalReps += rep;
    }

    if (totalReps === maxReps) {
        return "5x5";
    }

    return repString.slice(0, repString.length - 2);
}

function getOverheadPressRepsDispay(workout) {
    const overheadPressReps = workout.overheadPressReps;
    const maxReps = 25;
    let totalReps = 0;
    let repString = "";
    
    for (let rep of overheadPressReps) {
        repString += rep + " | ";
        totalReps += rep;
    }

    if (totalReps === maxReps) {
        return "5x5";
    }

    return repString.slice(0, repString.length - 2);
}

function getDeadliftRepsDispay(workout) {
    const deadliftReps = workout.deadliftReps;
    const maxReps = 5;
    let totalReps = 0;
    let repString = "";
    
    for (let rep of deadliftReps) {
        repString += rep + " | ";
        totalReps += rep;
    }

    if (totalReps === maxReps) {
        return "1x5";
    }

    return repString.slice(0, repString.length - 2);
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const workoutHistory = new WorkoutHistory();
    workoutHistory.mount();
};

window.addEventListener('DOMContentLoaded', main);