# Print the content of an array of 16 numbers, 4 numbers at a time, 
# using just the “each” Ruby construct.  Do the same using “each_slice” in “Enumerable.”

# Write a simple grep that will print the lines of a file having any occurrences of a 
# phrase anywhere in that line.  You will need to do a simple, regular expression match and read lines from a file.
require 'enumerator'

puts "Creating a array for random 16 numbers between 1 - 100"
$main_arr = Array.new(16) { rand(1...100) }
$tmp_arr = []
$counter = 0

puts "Printing array of length 16, 4 numbers at a time."
$main_arr.each do |i|
   $tmp_arr.push(i)
   if $tmp_arr.length === 4 || $counter === $main_arr.length - 1
   	puts $tmp_arr.join(",")
   	$tmp_arr = []
   end
   $counter += 1
end

puts "Printing array of length 16, 4 numbers at a time using “each_slice”"
$main_arr.each_slice(4).with_index { |(a, b, c, d), i| 
	if b == nil
		puts "#{a}"
	elsif c == nil
		puts "#{a}, #{b}"
	elsif d == nil
		puts "#{a}, #{b}, #{c}"
	else
		puts "#{a}, #{b}, #{c}, #{d}"
	end 
	}

def grep_file()
	line_count = 0

	puts "Enter the name of the file you want to grep"
	$file_name = gets.chomp

	puts "Enter the string you want to grep for.."
	$search_str = gets.chomp

	puts ""
	puts "Looking for all occurrences of\n" + $search_str + "\nin " + $file_name
	puts ""

	
	file = File.open($file_name).read
	
	if file == nil
		puts "Error: no file " + $file_name + " found in the system."
		return false
	end

	file.gsub!(/\r\n?/, "\n")

	file.each_line do |line|
		if line.gsub!( /#{$search_str}/, '"matched string"' )
			puts "Found in line " + (line_count + 1).to_s + " - " + line
		end
		line_count += 1
	end

end

grep_file()





