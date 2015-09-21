# TimeWAV
Stitch or merge wav audio files based on time (day, week, month, year)


Please see the following for the original WAV project.
https://github.com/JonMercer/Join-Daily-Wave-Files-to-Weekly-Wave-Files

I'm currently revising the above project to be more feature-rich. Also, looking back at it, that code was terrible

#TODO

- Refactor deeply nested code
- Create interfaces
- Refactor classes if needed
- Create unit tests
- Think of extra features to add
- Think of creating a simple UI so that my mom can use this.
- Move merger filesystem commands to fileSystem
- Find a way to remove duplication of work when merging files that have already been merged
- Replace file strings with URLs


# Features
- Prevent merging if it already exists. Save processing time
- Make folder traversal recursive
- Ony merge ".wav" files instead of all files
- Make format more flexible. Change substring numbers and format "string"