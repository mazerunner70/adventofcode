package main

import (
	"mazerunner.com/aoc/internal/day01"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {
	// load a file and pass it in
	//data, err := os.ReadFile("../input.txt")
	//check(err)
	//part1Strategies := []func(string) int{part1.Part1, part1.Part1NoRegex, part1.Part1Multithread}
	//for _, strategy := range part1Strategies {
	//	result := strategy(string(data))
	//	fmt.Println(result)
	//}
	day01.Day01()
}
