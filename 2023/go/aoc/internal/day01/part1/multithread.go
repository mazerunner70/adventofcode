package part1

import (
	"mazerunner.com/aoc/internal/utils"
	"strconv"
	"strings"
	"sync"
	"unicode"
)

func processLine(line string) int {
	firstValue := ""
	lastValue := ""
	for _, char := range line {
		if unicode.IsDigit(char) {
			if firstValue == "" {
				firstValue = string(char)
			}
			lastValue = string(char)
		}
	}
	i, _ := strconv.Atoi(firstValue + lastValue)
	return i
}

func Multithread(data string) (int, string) {
	defer utils.Timer()()
	endTimer := utils.DeclTimer()
	lines := strings.Split(data, "\n")
	var wg sync.WaitGroup
	messages := make(chan int, len(lines))
	for _, line := range lines {
		wg.Add(1)
		go func(line string) {
			defer wg.Done()
			messages <- processLine(line)
		}(line)
	}
	wg.Wait()
	sum := 0
	for i := 0; i < len(lines); i++ {
		sum += <-messages
	}
	return sum, endTimer()
}
