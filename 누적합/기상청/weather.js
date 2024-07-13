// case 1.
const fs = require('fs');
const path = require('path');

const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : path.join(__dirname, '/input.txt')).toString();
const lines = input.split('\n');

const [n, m] = lines[0].split(' ').map(Number);
const numbers = lines[1].split(' ').map(Number);

function accumulator (days, continuous, temperature) {
  let currentTemperature = 0;

  for (let i = 0; i < continuous; i++) {
    currentTemperature += temperature[i];
  }
  let topTemperature = currentTemperature

  // sliding window algorithm
  for (let i = continuous; i < days; i++) {
    currentTemperature += temperature[i] - temperature[i - continuous]

    if (topTemperature < currentTemperature) {
      topTemperature = currentTemperature
    }
  }
  return topTemperature
}

console.log(accumulator (n,m, numbers));
