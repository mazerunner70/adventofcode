package part1

import (
	"mazerunner.com/aoc/internal/utils"
	"regexp"
	"strconv"
	"strings"
)

// Hello returns a greeting for the named person.
func WithRegex(data string) (int, string) {
	defer utils.Timer()()
	endTimer := utils.DeclTimer()
	lines := strings.Split(data, "\n")
	sum := 0
	for _, items := range lines {
		converted_string := string(items)
		first := ""
		second := ""
		for _, char := range converted_string {
			converted_char := string(char)
			isNumber := regexp.MustCompile(`[0-9]`).MatchString(converted_char)
			if isNumber && first == "" {
				first = converted_char
			}
			if isNumber {
				second = converted_char
			}
		}
		n, _ := strconv.Atoi(first + second)
		sum += n
	}
	return sum, endTimer()
}
