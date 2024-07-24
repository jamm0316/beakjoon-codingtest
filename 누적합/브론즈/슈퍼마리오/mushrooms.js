// Read factor
const fs = require('fs');
const path = require('path');

const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : path.join(__dirname, '/input.txt'));
const mushrooms = input.toString().split('\n').map(Number);

// init constant, variable
const TARGET = 100;
let totalScore = 0;
let closestScore = 0;

// roof mushrooms
for (let i = 0; i <= mushrooms.length; i++) {
  totalScore += mushrooms[i];

  if (Math.abs(TARGET - totalScore) <= Math.abs(TARGET - closestScore)) {
    closestScore = totalScore;
  }
}

// return closestScore
console.log(closestScore);
