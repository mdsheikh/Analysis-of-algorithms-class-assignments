
import random
import time
import sys
sys.setrecursionlimit(10000)


def partition(arr, l, r):
    x = arr[r]
    i = l
    for j in range(l, r):

        if arr[j] <= x:
            arr[i], arr[j] = arr[j], arr[i]
            i += 1

    arr[i], arr[r] = arr[r], arr[i]
    return i


def QuickSelectRandomPivot(arr, l, r, k):
    global count

    if (k > 0 and k <= r - l + 1):

        index = partition(arr, l, r)

        # if position is same as k
        count = count + 1
        if (index - l == k - 1):
            return arr[index]
        count = count + 1
        if (index - l > k - 1):
            return QuickSelectRandomPivot(arr, l, index - 1, k)

        # Else recur for right subarray
        return QuickSelectRandomPivot(arr, index + 1, r, k - index + l - 1)

    return -1


def median_of_medians(A, i):

    global counts
    # divide A into sublists of len 5
    sublists = [A[j:j+5] for j in range(0, len(A), 5)]
    medians = [sorted(sublist)[len(sublist)//2] for sublist in sublists]
    if len(medians) <= 5:
        pivot = sorted(medians)[len(medians)//2]
    else:
        # the pivot is the median of the medians
        pivot = median_of_medians(medians, len(medians)//2)

    # partitioning step
    low = [j for j in A if j < pivot]
    high = [j for j in A if j > pivot]

    k = len(low)
    counts += 1
    if i < k:
        return median_of_medians(low, i)
    elif i > k:
        counts += 1
        return median_of_medians(high, i-k-1)
    else:  # pivot = k

        return pivot


if __name__ == "__main__":

    dataset = list(range(1, 10001, 1))  # list of 10000 elements
    random.shuffle(dataset)  # dataset has been shuffled
    # print(dataset)
    n = len(dataset)   # length of the dataset
    print("\n")
    print("TRIAL#: ", " SIZE",  "\t\tQSRP: ", "\t CLOCK_TIME_QSRP: ", "\tCOMPARISON_QSRP"
          "\t\tQSMM ", "\t\tCLOCKTIME_QSMM: ", "COMPARISONS_QSMM: ")

    for i in range(1, 11):
        count = 0
        counts = 0
        print(i, end="  ")
        print('%12d' % n, end="  ")
        st1 = time.time_ns()
        print('%12d' % QuickSelectRandomPivot(dataset, 0, n-1,  n//2), end=" ")
        et1 = time.time_ns()
        total = et1 - st1
        print('%15d' % total, "ns", end=" ")
        print('%12d' % count, end=" ")
        st = time.time_ns()
        print('%22d' % median_of_medians(dataset, (n//2)), end=" ")
        et = time.time_ns()
        totals = et - st
        print('%18d' % totals, "ns", end="  ")
        print('%15d' % counts)
        print()
