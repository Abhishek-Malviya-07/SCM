document.addEventListener("DOMContentLoaded", () => {
    console.log("Script is Here");

    let currentTheme = getTheme();  // Get the current theme from localStorage
    applyTheme(currentTheme);       // Apply the theme when the page loads

    // Initialize the theme switcher event listener
    setupThemeSwitcher(currentTheme);
});

function setupThemeSwitcher(currentTheme) {
    const changeThemeButton = document.querySelector('#theme_change_button');
    updateButtonText(changeThemeButton, currentTheme);  // Set initial button text

    changeThemeButton.addEventListener('click', () => {
        const newTheme = toggleTheme(currentTheme);

        // Update the theme in localStorage and on the document
        switchTheme(currentTheme, newTheme);
        setTheme(newTheme);

        currentTheme = newTheme;  // Update the current theme
        updateButtonText(changeThemeButton, currentTheme);  // Update button text
    });
}

function toggleTheme(theme) {
    return theme === "dark" ? "light" : "dark";  // Toggle between "dark" and "light"
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);  // Save the theme to localStorage
}

function getTheme() {
    return localStorage.getItem("theme") || "light";  // Default to light if no theme is set
}

function applyTheme(theme) {
    document.documentElement.classList.add(theme);  // Apply the theme class to the HTML element
}

function switchTheme(oldTheme, newTheme) {
    document.documentElement.classList.replace(oldTheme, newTheme);  // Efficiently replace the theme class
}

function updateButtonText(button, theme) {
    button.querySelector("span").textContent = theme === "light" ? "Dark" : "Light";  // Update the button text
}
