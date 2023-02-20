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
            'finishWorkoutB', 'startTimer', 'isLoggedIn', 'isCurrentUser', 'isWorkoutComplete'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    async mount() {
        document.getElementById('workoutSubmitButton').addEventListener('click', this.finishWorkout);
        document.getElementById('timerButton').addEventListener('click', this.startTimer);
        this.header.addHeaderToPage();
        this.client = new FiveLiftsClient();
        await this.isLoggedIn();
        await this.isCurrentUser();
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
            const completedWorkout = await this.finishWorkoutA();
            this.dataStore.set('completedWorkout', completedWorkout);
        } else {
            const completedWorkout = await this.finishWorkoutB();
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
    
        return await this.client.addWorkout(date, workoutType, timeStarted,
            timeEnded, squatWeight, benchPressWeight, overheadPressWeight, barbellRowWeight,
            deadliftWeight, squatReps, benchPressReps, overheadPressReps,
            barbellRowReps, deadliftReps, bodyWeight, (error) => {
                const errorBlock = document.getElementById('errorMessage');
                errorBlock.innerText = `${error.message}`;
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
    
        return await this.client.addWorkout(date, workoutType, timeStarted,
            timeEnded, squatWeight, benchPressWeight, overheadPressWeight, barbellRowWeight,
            deadliftWeight, squatReps, benchPressReps, overheadPressReps,
            barbellRowReps, deadliftReps, bodyWeight, (error) => {
                const errorBlock = document.getElementById('errorMessage');
                errorBlock.classList.remove('hidden');
                errorBlock.innerText = `${error.message}`;
                document.getElementById('workoutSubmitButton').innerText = 'FINISH WORKOUT';
        });
    }

    /**
     * Method to redirect to Upcoming Workouts page.
     */
    async redirectToUpcomingWorkouts() {
        const completedWorkout = this.dataStore.get('completedWorkout');
        if (completedWorkout != null) {
            window.location.href = `/upcomingWorkouts.html`;
        }
    }

    /**
     * Method to start rest timer.
     */
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
            const currentWorkoutCard = document.getElementById('currentWorkoutCard');
            const timerButton = document.getElementById('timer');
            const finishWorkoutButton = document.getElementById('finishWorkoutButton');
            timerButton.classList.add('hidden');
            finishWorkoutButton.classList.add('hidden');
            currentWorkoutCard.innerHTML = `
            <div id="currentWorkoutNonUserPopUp">
                <h1 class="notCurrentUser">Please enter your starting weights first.</h1>
                <button class="startingWeightsButton" id="startingWeightsButton" type="button">ENTER STARTING WEIGHTS</button>
            </div>
            `;
            document.getElementById('startingWeightsButton').addEventListener('click', this.redirectToStartingWeights);
        } else {
            this.isWorkoutComplete();
        }
    }
    /**
     * Method to check if workout is already done for the day.
     */
    async isWorkoutComplete() {
        const mostRecentWorkout = await this.client.getMostRecentWorkout();
        const mostRecentWorkoutDate = mostRecentWorkout.workoutDate;
        const today = new Date();
        const todayMonth = today.getMonth() + 1;
        const todayDate = today.getDate();
        const workoutMonth = mostRecentWorkoutDate[1];
        const workoutDate = mostRecentWorkoutDate[2];

        if (todayMonth === workoutMonth && 
            todayDate === workoutDate) {
            const currentWorkoutTitle = document.getElementById('currentWorkoutTitle');
            currentWorkoutTitle.classList.add('hidden');
            const timerButton = document.getElementById('timerButton');
            timerButton.classList.add('hidden');
            const workoutSubmitButton = document.getElementById('workoutSubmitButton');
            workoutSubmitButton.classList.add('hidden');
            const currentWorkoutCard = document.getElementById('currentWorkoutCard');
            currentWorkoutCard.innerHTML = `
            <h1 class="workoutComplete">Workout complete. Great job!</h1>
            `;
        } else {
            this.displayCurrentWorkout();
        }
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
                <input class="squatInput" type="number" required class="validated-field" id="squatSet1" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet2" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet3" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet4" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet5" min="1" max="5" placeholder="0" 
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
                <input type="number" required class="validated-field" id="benchPressSet1" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet2" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet3" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet4" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="benchPressSet5" min="1" max="5" placeholder="0" 
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
            <input type="number" required class="validated-field" id="barbellRowSet1" min="1" max="5" placeholder="0" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet2" min="1" max="5" placeholder="0" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet3" min="1" max="5" placeholder="0" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet4" min="1" max="5" placeholder="0" 
            autofocus>
            <input type="number" required class="validated-field" id="barbellRowSet5" min="1" max="5" placeholder="0" 
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
                <input type="number" required class="validated-field" id="squatSet1" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet2" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet3" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet4" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="squatSet5" min="1" max="5" placeholder="0" 
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
                <input type="number" required class="validated-field" id="overheadPressSet1" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet2" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet3" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet4" min="1" max="5" placeholder="0" 
                autofocus>
                <input type="number" required class="validated-field" id="overheadPressSet5" min="1" max="5" placeholder="0" 
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
            <input type="number" required class="validated-field" id="deadliftSet1" min="1" max="5" placeholder="0" 
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
    
    if (date < 10 && month < 10) {
        return `${year}-0${Number(month) + 1}-0${date}`;
    } 
    
    if (month < 10) {
        return `${year}-0${Number(month) + 1}-${date}`
    }

    if (date < 10) {
        return `${year}-${Number(month) + 1}-0${date}`
    }

    else {
        return `${year}-0${Number(month) + 1}-${date}`
    }
}

 function getTimeString(time) {
    return time.toISOString();
}

function getSquatReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "squatSet" + (i + 1);
        let repValue = document.getElementById(setNumber).value;
        if (repValue === "") {
            repValue = 0;
        }
        reps[i] = repValue;
    }

    return reps;
}

function getBenchPressReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "benchPressSet" + (i + 1);
        let repValue = document.getElementById(setNumber).value;
        if (repValue === "") {
            repValue = 0;
        }
        reps[i] = repValue;
    }

    return reps;
}

function getBarbellRowReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "barbellRowSet" + (i + 1);
        let repValue = document.getElementById(setNumber).value;
        if (repValue === "") {
            repValue = 0;
        }
        reps[i] = repValue;
    }

    return reps;
}

function getOverheadPressReps() {
    const reps = [];

    for (let i=0; i < 5; i++) {
        let setNumber = "overheadPressSet" + (i + 1);
        let repValue = document.getElementById(setNumber).value;
        if (repValue === "") {
            repValue = 0;
        }
        reps[i] = repValue;
    }

    return reps;
}

function getDeadliftReps() {
    const reps = [];

    let repValue = document.getElementById('deadliftSet1').value;
    if (repValue === "") {
        repValue = 0;
    }
    reps[0] = repValue;

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
