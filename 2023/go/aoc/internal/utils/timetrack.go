package utils

import (
	"fmt"
	"runtime"
	"time"
)

// callerName returns the name of the function skip frames up the call stack.
func callerName(skip int) string {
	const unknown = "unknown"
	pcs := make([]uintptr, 1)
	n := runtime.Callers(skip+2, pcs)
	if n < 1 {
		return unknown
	}
	frame, _ := runtime.CallersFrames(pcs).Next()
	if frame.Function == "" {
		return unknown
	}
	return frame.Function
}

// timer returns a function that prints the name of the calling
// function and the elapsed time between the call to timer and
// the call to the returned function. The returned function is
// intended to be used in a defer statement:
//
//	defer timer()()
func Timer() func() {
	name := callerName(1)
	start := time.Now()
	return func() {
		fmt.Printf("%s took %v\n", name, time.Since(start))
	}
}

func DeclTimer() func() string {
	start := time.Now()
	return func() string {
		return fmt.Sprintf("%v", time.Since(start))
	}
}
