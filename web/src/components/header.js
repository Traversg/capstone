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
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        // const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        const navBar = document.createElement('nav');
        navBar.innerHTML = `
        <ul>
            <li class="dropdown">
                <button class="dropbtn">Progress</button>
                <div class="dropdown-content">
                    <a href="/progress/squatProgress.html">Squat</a>
                    <a href="/progress/benchPressProgress.html">Bench Press</a>
                    <a href="/progress/barbellRowProgress.html">Barbell Row</a>
                    <a href="/progress/overheadPressProgress.html">Overhead Press</a>
                    <a href="/progress/deadliftProgress.html">Deadlift</a>
                </div>
            </li>    
            <li><a href="/upcomingWorkouts.html">Upcoming Workouts</a></li>
            <li><a href="/workoutHistory.html">Workout History</a></li>
            <li><button class="logoutButton" id="logout" type="button">Logout</button></li>
        </ul>`;
        header.appendChild(navBar);
        // header.appendChild(userInfo);
        document.getElementById('logout').addEventListener('click', this.client.logout);
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


/*    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
    */
}
