package day01

import (
	"embed"
	"github.com/jedib0t/go-pretty/v6/table"
	"mazerunner.com/aoc/internal/day01/part1"
	"mazerunner.com/aoc/internal/day01/part2"
	"os"
	"strconv"
)

// https://github.com/jedib0t/go-pretty/tree/main/table

//go:embed inputs/*
var folder embed.FS

type challengerun struct {
	day       int
	part      int
	inputfile string
	algorithm string
	result    string
	timetaken string
}

func tabularoutput(rows []challengerun) {
	t := table.NewWriter()
	t.SetOutputMirror(os.Stdout)
	t.AppendHeader(table.Row{"Day", "Part", "Input file", "Algorithm", "Result", "Time Taken"})
	for _, row := range rows {
		t.AppendRows([]table.Row{
			{row.day, row.part, row.inputfile, row.algorithm, row.result, row.timetaken},
		})
	}
	t.Render()
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

type strategy struct {
	name string
	impl func(string) (int, string)
}

func runTestSuite(strategies []strategy, partNo int) {
	results := []challengerun{}
	for _, filename := range []string{"inputs/test.txt", "inputs/input.txt"} {
		for _, strat := range strategies {
			data, err := folder.ReadFile(filename)
			check(err)
			result, timer := strat.impl(string(data))
			results = append(results, challengerun{1, partNo, filename, strat.name, strconv.Itoa(result), timer})
		}
	}
	tabularoutput(results)
}

func Day01() {
	part1Strategies := []strategy{
		{"regex", part1.WithRegex},
		{"no regex", part1.NoRegex},
		{"multithread", part1.Multithread}}
	part2Strategies := []strategy{
		{"regex", part2.Part2withRegex}}

	runTestSuite(part1Strategies, 1)
	runTestSuite(part2Strategies, 2)
}
