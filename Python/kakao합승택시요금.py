import math
def solution(n, s, a, b, fares):
    INF = math.inf
    answer = math.inf
    arr = [[0 if i == j  else INF for i in range(n)] for j in range(n)]
    for i in range(len(fares)):
        x, y, w = fares[i]
        arr[x - 1][y - 1] = w
        arr[y - 1][x - 1] = w

    for i in range(n):
        for j in range(n):
            for k in range(n):
                arr[j][k] = min(arr[j][i] + arr[i][k], arr[j][k])

    for i in range(n):
        val = arr[s - 1][i] + arr[i][a - 1] + arr[i][b - 1]
        answer = min(answer, val)
    
    return answer

solution(7, 3, 4, 1, 	[[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]])