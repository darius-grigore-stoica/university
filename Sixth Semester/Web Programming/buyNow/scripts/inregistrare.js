document.addEventListener('DOMContentLoaded', function() {
    const usernameInput = document.getElementById('username');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const phoneInput = document.getElementById('phone');
    const birthDateInput = document.getElementById('birthDate');
    const dateFormatSelect = document.getElementById('dateFormat');
    const rolSelect = document.getElementById('rol'); // Asigură-te că ai un select cu id="rol"

    // Evenimente de validare live
    birthDateInput.addEventListener('input', validateAllFields);
    dateFormatSelect.addEventListener('change', validateAllFields);
    usernameInput.addEventListener('input', validateAllFields);
    emailInput.addEventListener('input', validateAllFields);
    passwordInput.addEventListener('input', validateAllFields);
    confirmPasswordInput.addEventListener('input', validateAllFields);
    phoneInput.addEventListener('input', validateAllFields);

    function validateAllFields() {
        validateUsername();
        validateEmail();
        validatePassword();
        validateConfirmPassword();
        validatePhone();
        validateBirthDate();
    }

    function validateUsername() {
        const isValid = /^[a-z0-9]+$/.test(usernameInput.value);
        updateRequirement('username-req', isValid);
        updateValidationUI(usernameInput, isValid, 'Doar litere mici și cifre');
    }

    function validateEmail() {
        const email = emailInput.value;
        const isValid = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email) && 
                        (email.match(/@/g)?.length === 1);
        updateValidationUI(emailInput, isValid, 'Adresă email invalidă');
    }

    function validatePassword() {
        const value = passwordInput.value;
        const hasLower = /[a-z]/.test(value);
        const hasUpper = /[A-Z]/.test(value);
        const hasNumber = /[0-9]/.test(value);
        const hasSpecial = /!/.test(value);

        updateRequirement('lower-req', hasLower);
        updateRequirement('upper-req', hasUpper);
        updateRequirement('number-req', hasNumber);
        updateRequirement('special-req', hasSpecial);

        const isValid = hasLower && hasUpper && hasNumber && hasSpecial;
        updateValidationUI(passwordInput, isValid, 'Parolă invalidă');
    }

    function validateConfirmPassword() {
        const isMatch = confirmPasswordInput.value === passwordInput.value;
        updateValidationUI(confirmPasswordInput, isMatch, 'Parolele nu coincid');
    }

    function validatePhone() {
        const isValid = /^\(\+40\) [0-9]{3} [0-9]{3} [0-9]{3}$/.test(phoneInput.value);
        updateValidationUI(phoneInput, isValid, 'Format: (+40) 777 777 777');
    }

    function validateBirthDate() {
        const dateStr = birthDateInput.value;
        const format = dateFormatSelect.value;
        const isValid = validateDateValue(dateStr, format);

        updateRequirement('format-req', isValid);
        updateValidationUI(birthDateInput, isValid, 'Data nu este în formatul corect');
    }

    function validateDateValue(dateStr, format) {
        if (!dateStr) return false;
        const parts = dateStr.split('/');
        if (parts.length !== 3) return false;

        let day, month, year;

        if (format === 'zz/ll/aaaa') {
            [day, month, year] = parts.map(Number);
        } else if (format === 'll/zz/aaaa') {
            [month, day, year] = parts.map(Number);
        } else if (format === 'zz/ll/aa') {
            day = parseInt(parts[0], 10);
            month = parseInt(parts[1], 10);
            year = 2000 + parseInt(parts[2], 10);
        }

        if (isNaN(day) || isNaN(month) || isNaN(year)) return false;
        if (year < 1900 || year > new Date().getFullYear()) return false;
        if (month < 1 || month > 12) return false;

        const daysInMonth = new Date(year, month, 0).getDate();
        if (day < 1 || day > daysInMonth) return false;

        const inputDate = new Date(year, month - 1, day);
        return inputDate <= new Date();
    }

    function updateRequirement(elementId, isValid) {
        const element = document.getElementById(elementId);
        if (element) {
            element.querySelector('i').className = isValid ? 'fas fa-check-circle' : 'fas fa-times-circle';
            element.className = isValid ? 'requirement valid' : 'requirement invalid';
        }
    }

    function updateValidationUI(inputElement, isValid, errorMessage) {
        const errorElement = inputElement.nextElementSibling;
        if (errorElement && errorElement.classList.contains('error-message')) {
            errorElement.textContent = isValid ? '' : errorMessage;
        }
        inputElement.style.borderColor = isValid ? '#4CAF50' : '#f44336';
    }

    function parseDate(input, format) {
        const parts = input.split('/');
        if (parts.length !== 3) return null;

        let zz, ll, aaaa;

        if (format === 'zz/ll/aaaa') {
            [zz, ll, aaaa] = parts;
        } else if (format === 'll/zz/aaaa') {
            [ll, zz, aaaa] = parts;
        } else if (format === 'zz/ll/aa') {
            [zz, ll, aaaa] = parts;
            aaaa = parseInt(aaaa) > 30 ? '19' + aaaa : '20' + aaaa;
        } else {
            return null;
        }

        return `${aaaa}-${ll}-${zz}`;
    }

    document.getElementById("register-form").addEventListener("submit", function (e) {
        console.log("a intrat");
        e.preventDefault();
        validateAllFields();

        const parola = passwordInput.value;
        const confirmParola = confirmPasswordInput.value;
        const username = usernameInput.value;
        const email = emailInput.value;
        const telefon = phoneInput.value;
        const rawDate = birthDateInput.value;
        const selectedFormat = dateFormatSelect.value;
        const rol = rolSelect.value;

        const dataNastere = parseDate(rawDate, selectedFormat);

        if (!dataNastere) {
            alert("Data de naștere are un format invalid.");
            return;
        }

        const invalidFields = [...document.querySelectorAll('.invalid')];
        if (invalidFields.length > 0) {
            alert('Te rugăm să completezi corect toate câmpurile!');
            return;
        }

        if (parola !== confirmParola) {
            alert("Parolele nu se potrivesc.");
            return;
        }
    });
});
