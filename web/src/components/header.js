import FiveLiftsClient from '../api/fiveLiftsClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle'];
        this.bindClassMethods(methodsToBind, this);

        this.client = new FiveLiftsClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const siteTitle = this.createSiteTitle();

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        const navBar = document.createElement('nav');
        navBar.innerHTML = `
        <div class="fullNav">
        <ul>
            <li class="dropdown">
                <button class="dropbtn">Progress</button>
                <div class="dropdown-content">
                    <a href="/squatProgress.html">Squat</a>
                    <a href=/benchPressProgress.html">Bench Press</a>
                    <a href="/barbellRowProgress.html">Barbell Row</a>
                    <a href="/overheadPressProgress.html">Overhead Press</a>
                    <a href="/deadliftProgress.html">Deadlift</a>
                </div>
            </li>    
            <li><a href="/upcomingWorkouts.html">Upcoming Workouts</a></li>
            <li><a href="/workoutHistory.html">Workout History</a></li>
            <li><button class="logoutButton" id="logout" type="button">Logout</button></li>
        </ul>
        </div>
        <div class="hamburgerNav">
            <button type="button" class="hamburgerButton" id="hamburgerButton">&#9776</button>
        </div>
        `;
        header.appendChild(navBar);

        document.getElementById('logout').addEventListener('click', this.client.logout);
        document.getElementById('hamburgerButton').addEventListener('click', this.displayMobileMenu);
    }

    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'FIVELIFTS';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    displayMobileMenu() {
        document.getElementById('background').style.backgroundColor = "#ff0024";
        document.getElementById('hamburgerButton').innerText = "x";
        document.getElementById('workoutHistoryCard').innerHTML = `
        <div class="mobileMenu" id="mobileMenu">
        <div class="dropdown">
            <button class="dropbtn">Progress</button>
            <div class="dropdown-content">
                <a href="/squatProgress.html">Squat</a>
                <a href=/benchPressProgress.html">Bench Press</a>
                <a href="/barbellRowProgress.html">Barbell Row</a>
                <a href="/overheadPressProgress.html">Overhead Press</a>
                <a href="/deadliftProgress.html">Deadlift</a>
            </div>
        </div>    
        <a href="/upcomingWorkouts.html">Upcoming Workouts</a>
        <a href="/workoutHistory.html">Workout History</a>
        <button class="logoutButtonMobile" id="logout" type="button">Logout</button>
        </div>
    `;
    document.getElementById('hamburgerButton').addEventListener('click', this.closeMobileMenu);
    }

    closeMobileMenu() {
        const mobileMenu = document.getElementById('mobileMenu');
        mobileMenu.classList.add('hidden');
    }
}
