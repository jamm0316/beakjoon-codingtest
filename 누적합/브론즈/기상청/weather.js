// case 1. window sliding algoritm
// read input
const fs = require('fs');
const path = require('path');

const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : path.join(__dirname, '/input.txt')).toString();
const lines = input.split('\n');

const [n, m] = lines[0].split(' ').map(Number);
const numbers = lines[1].split(' ').map(Number);

// defind function
function accumulator (days, continuous, temperature) {
  let currentTemperature = 0;

  // step 1. initialize currnetTemperature
  for (let i = 0; i < continuous; i++) {
    currentTemperature += temperature[i];
  }
  let topTemperature = currentTemperature

  // step 2. sliding window algorithm
  for (let i = continuous; i < days; i++) {
    currentTemperature += temperature[i] - temperature[i - continuous]

    if (topTemperature < currentTemperature) {
      topTemperature = currentTemperature
    }
  }
  return topTemperature
}

console.log(accumulator (n,m, numbers));


// case 2. reduce method
const fs = require('fs');
const path = require('path');

const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : path.join(__dirname, 'input.txt')).toString();
const lines = input.split('\n');

const [n, m] = lines[0].split(' ').map(Number);
const numbers = lines[1].split(' ').map(Number);

function accumulator(days, continuous, temperature) {
  // step 1. initialize variables
  let topTemperature = Number.MIN_SAFE_INTEGER;
  let currentTemperature = 0;
  
  // step 2. loof using reduce method
  for (let i = 0; i <= days - continuous; i++) {
    currentTemperature = temperature.slice(i, i + continuous).reduce((acc, currentValue) => acc + currentValue, 0);

    if (currentTemperature > topTemperature) {
      topTemperature = currentTemperature;
    }
  }
  return topTemperature;
}

console.log(accumulator(n, m, numbers));
