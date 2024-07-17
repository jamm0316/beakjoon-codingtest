const fs = require('fs');
const input = fs.readFileSync('/dev/stdin', 'utf8').trim().split(/\s+/);
const lines = input.trim().split('\n');
let idx = 0;

// Read N and M
const [N, M] = lines[idx].split(' ').map(Number);
idx += 1;

// Read the array
const array = [];
for (let i = 0; i < N; i++) {
array.push(lines[idx].split(' ').map(Number));
idx += 1;
    }

    // Create the prefix sum array
function solution(array, N, M) {
    const prefixSum = Array.from(Array(N + 1), () => Array(M + 1).fill(0));

    // Fill the prefix sum array
    for (let i = 1; i <= N; i++) {
        for (let j = 1; j <= M; j++) {
            prefixSum[i][j] = array[i - 1][j - 1] +
                              prefixSum[i - 1][j] +
                              prefixSum[i][j - 1] -
                              prefixSum[i - 1][j - 1];
        }
    }

    // Read K
    const K = Number(lines[idx]);
    idx += 1;

    const results = [];
    for (let k = 0; k < K; k++) {
        const [i, j, x, y] = lines[idx].split(' ').map(Number);
        idx += 1;

        // Calculate the sum using the prefix sum array
        const sum = prefixSum[x][y] -
                    prefixSum[i - 1][y] -
                    prefixSum[x][j - 1] +
                    prefixSum[i - 1][j - 1];

        results.push(sum);
    }

    // Output all results
    console.log(results.join('\n'));
}

solution(array, N, M);
