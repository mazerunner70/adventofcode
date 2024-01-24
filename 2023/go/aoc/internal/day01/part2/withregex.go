package part2

import (
	"fmt"
	"mazerunner.com/aoc/internal/utils"
	"regexp"
	"strconv"
	"strings"
)

var numberText []string = []string{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}

// Get regex to find numbers in a string
var regexstring string = "(\\d|" + strings.Join(numberText, "|") + ")"

func Part2withRegex(data string) (int, string) {
	defer utils.Timer()()
	endTimer := utils.DeclTimer()
	fmt.Print("regexstring: ", regexstring)
	lines := strings.Split(data, "\n")
	sum := 0
	for _, line := range lines {
		converted_string := string(line)
		first := ""
		second := ""
		matches := regexp.MustCompile(regexstring).FindStringSubmatch(converted_string)
		for _, match := range matches {
			fmt.Println("match: ", match)
			if first == "" {
				first = match
			}
			second = match
		}
		n, _ := strconv.Atoi(first + second)
		sum += n
	}
	return sum, endTimer()
}
