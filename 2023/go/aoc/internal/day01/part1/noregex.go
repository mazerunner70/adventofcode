package part1

import (
	"mazerunner.com/aoc/internal/utils"
	"strconv"
	"strings"
	"unicode"
)

// WithRegex without using regex
func NoRegex(data string) (int, string) {
	defer utils.Timer()()
	endTimer := utils.DeclTimer()
	lines := strings.Split(data, "\n")
	sum := 0
	for _, line := range lines {
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
		sum += i
	}
	return sum, endTimer()
}
