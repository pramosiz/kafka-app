const randomNumber = Math.floor(Math.random() * 200)

let amount = pm.globals.get("counter") || 0

console.log("Counter value init:" + amount)

amount = parseInt(amount) + 100

pm.globals.set("counter", amount)

pm.environment.set("counter", amount) // Update the environment variable "counter" with the new value

console.log("Counter value end:" + amount)

pm.variables.set("id", randomNumber)

// {
//     "id": {{id}},
//     "product": "Laptop",
//     "amount": {{counter}}
//   }