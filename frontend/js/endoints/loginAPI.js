async function loginAPI(authData) {

    const response = await fetch('http://localhost:8080/api/v1/user/auth',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(authData)
    })
    .then(res => res.json())
    .catch(error => console.log(error))

    return response;
}