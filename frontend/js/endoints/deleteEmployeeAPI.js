async function deleteEmployeeAPI(empId) {

    const response = await fetch(`http://localhost:8080/api/v1/employee/${empId}`, {
        method: 'DELETE',
        headers: {
            'content-Type': 'application/json'
        }
    })
    .then(res => res.json())
    .catch(err => console.log(err)  )

    return response;
}