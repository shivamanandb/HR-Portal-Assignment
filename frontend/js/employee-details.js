const addEmployee = document.getElementById('add-employee');
const modalContainer = document.getElementById('modal_container');
const closeBtn = document.getElementById('close-btn');
const editContainer = document.getElementById('edit_container');
const editCloseBtn = document.getElementById('edit-close-btn');
const deleteContainer = document.getElementById('delete-container');
const deleteYesBtn = document.getElementById('yes-btn');
const deleteNoBtn = document.getElementById('no-btn');
const addEmpForm = document.getElementById('add-employee-form');
const updationForm = document.getElementById('updation-form');
const handleError = document.querySelector('.error-section');
const logoutBtn = document.getElementById('logout');

let currentEmployeeId = null;
let data = null;

addEmployee.addEventListener('click', () => {
    modalContainer.classList.add('show');
})

closeBtn.addEventListener('click', () => {
    modalContainer.classList.remove('show');
})

deleteNoBtn.addEventListener('click', () => {
    deleteContainer.classList.remove('show');
})

document.addEventListener('DOMContentLoaded', async()=>{
    data = await fetchEmployeesAPI();
    console.log(data);
    renderTable(data);
})


addEmpForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const name = document.getElementById('name').value;
    const department = document.getElementById('department').value;
    const salary = document.getElementById('salary').value;
    const email = document.getElementById('email').value;
    setupAddEmployeeData(
    {
        name: name,
        department: department, 
        salary: salary, 
        email: email
    })
})

function showError(message){
    handleError.innerHTML = '';
    const element = document.createElement('h2');
    element.innerText = message.substring(7) + " !!";
    element.classList.add('error-handler');
    element.style.color = 'red';
    handleError.appendChild(element);
}


async function setupAddEmployeeData(data) {
    // console.log("currentEmployeeId:", currentEmployeeId);
    // console.log("data: ", data)
    const res = await addEmployeeAPI(data);

    if(res && res.message && res.message.startsWith("Error:")){
        showError(res.message)
    }else{
        modalContainer.classList.remove('show');
        addEmpForm.reset();
        data = await fetchEmployeesAPI();
        renderTable(data);
    }
}


editCloseBtn.addEventListener('click', () => {
    editContainer.classList.remove('show'); 
    handleError.innerHTML = '';
})

const renderTable = (data) => {
    
    const tableBody = document.querySelector('.table-body');
    tableBody.innerHTML = '';
    
    data.forEach(emp => {
        
        const row = document.createElement('tr');

        row.innerHTML = `
                    <td>${emp.name}</td>
                    <td>${emp.department}</td>
                    <td>${emp.email}</td>
                    <td>${emp.salary}</td>
                    <td class="font-element" >
                        <i data-id="${emp.id}" data-name="${emp.name}" data-email="${emp.email}" data-sal="${emp.salary}" data-dept="${emp.department}" class="edit-btn fas fa-edit font-color"></i> 
                        <i data-id="${emp.id}" class="delete-btn fa-solid fa-trash font-color"></i>
                    </td>
        `
        tableBody.appendChild(row);
    });

    const editBtn = document.querySelectorAll('.edit-btn');
    editBtn.forEach(edit => {
        edit.addEventListener('click', ()=> {
            currentEmployeeId = edit.getAttribute('data-id');
            const currentEmployeeName = edit.getAttribute('data-name');
            const currentEmployeeEmail = edit.getAttribute('data-email');
            const currentEmployeeSalary = edit.getAttribute('data-sal');
            const currentEmployeeDept = edit.getAttribute('data-dept');
            const details = {
                name: currentEmployeeName,
                email: currentEmployeeEmail,
                salary: currentEmployeeSalary,
                department: currentEmployeeDept
            }
            populateUpdateForm(details, currentEmployeeId);
            editContainer.classList.add('show');
        })
    })

    const deleteBtn = document.querySelectorAll('.delete-btn');
    deleteBtn.forEach(del => {
        del.addEventListener('click', () => {
            currentEmployeeId = del.getAttribute('data-id');
            deleteContainer.classList.add('show');
        })
    })
}

function populateUpdateForm(details, empId) {
    document.getElementById('update-name').value = details.name;
    document.getElementById('update-department').value = details.department;
    document.getElementById('update-salary').value = details.salary;
    document.getElementById('update-email').value = details.email;
}

updationForm.addEventListener('submit', (event)=>{
    event.preventDefault();
    const name = document.getElementById('update-name').value;
    const department = document.getElementById('update-department').value;
    const salary = document.getElementById('update-salary').value;
    const email = document.getElementById('update-email').value;
    setupUpdateEmployeeData(
        {
            name: name,
            department: department, 
            salary: salary, 
            email: email
        })
})

async function setupUpdateEmployeeData(data) {
    // console.log("currentEmployeeId:", currentEmployeeId);
    // console.log("data: ", data)
    const res = await updateEmployeeDetailsAPI(currentEmployeeId, data);

    if(res.message && res.message.startsWith("Error:")){
        showError(res.message);
    }else{
        editContainer.classList.remove('show');
        data = await fetchEmployeesAPI();
        renderTable(data);
    }
}

deleteYesBtn.addEventListener('click', async()=>{
    const res = await deleteEmployeeAPI(currentEmployeeId);
    deleteContainer.classList.remove('show');
    data = await fetchEmployeesAPI();
    renderTable(data);
})

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('user');
    window.location.href = '/html/login.html';
})