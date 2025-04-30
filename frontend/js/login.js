document.addEventListener('DOMContentLoaded', () => {

    if(localStorage.getItem('user')){
        window.location.href = '/html/auth/employee-details.html';
        return;
    }
})

// form submission
const form = document.querySelector('form');
form.addEventListener('submit', async(event) => {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    const data = await loginAPI({email: email, password: password});

    if(data.message && data.message.startsWith("Error:")){
        
        const handleError = document.querySelector('.error-section');
        handleError.innerHTML='';
        const element = document.createElement('h2');
        element.innerText = data.message.substring(7)+" !!";
        element.style.color = 'red';
        handleError.appendChild(element);
    }else {
        localStorage.setItem('user', JSON.stringify(data));
        window.location.href = '/html/auth/employee-details.html';
    }
    console.log(data);
    // console.log("email: ", email);
    // console.log("password: ", password);
})