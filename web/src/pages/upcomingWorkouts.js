import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the starting weights page of the website.
 */
class UpcomingWorkouts extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'displayUpcomingWorkouts'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        this.header.addHeaderToPage();
        this.client = new FiveLiftsClient();
        this.displayUpcomingWorkouts();
    }

    /**
     * Method to run when mounted. Call the FiveLiftsService to get the
     * upcoming workouts.
     */

    async displayUpcomingWorkouts() {
        const upcomingWorkouts = await this.client.getUpcomingWorkouts();
        
        const firstWorkout = upcomingWorkouts[0];
        const secondWorkout = upcomingWorkouts[1];
        const thirdWorkout = upcomingWorkouts[2];

        for (let workout in upcomingWorkouts) {
            let workoutnumber = `workout${Number(workout) + 1}`;
            let currentWorkout = upcomingWorkouts[workout];
            //console.log(currentWorkout.workoutType);
            let workoutType = workoutTypeDisplay(currentWorkout.workoutType);
            //console.log(currentWorkout.workoutDate[1])
            let displayDate = getDisplayDate(currentWorkout.workoutDate[0],
                (currentWorkout.workoutDate[1] - 1), currentWorkout.workoutDate[2]);

            let workoutCard = document.getElementById(workoutnumber);
            workoutCard.innerHTML = `     
            <div class="workoutTypeAndDate">
                <p class="type">${workoutType}</p>
                <p class="date">${displayDate}</p> 
            </div>`;
        }
    }
}

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

function workoutTypeDisplay(workoutType) {
    let displayWorkoutType;
    if (workoutType == 'WORKOUT_A') {
        displayWorkoutType = 'Workout A';
    } else {
        displayWorkoutType = 'Workout B';
    }
    return displayWorkoutType;
    
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const upcomingWorkouts = new UpcomingWorkouts();
    upcomingWorkouts.mount();
};

window.addEventListener('DOMContentLoaded', main);