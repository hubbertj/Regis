$helloStr =  "Hello World"
$helloRuby = "Hello Ruby"
$tmpIndex = $helloRuby.index('Ruby')
$inx = 1

puts $helloStr

puts ""

while $inx <= 10  do
   puts("This is sentence number #$inx" )
   $inx +=1
end

puts ""


def guess_game()
	$randomNum = rand(100)
	puts "I am thinking of a number between 1 - 100, what is it ?"

	until ARGV.empty? do
		puts "From arguments: #{ARGV.shift}"
	end

	while a = gets
		if a.to_i > $randomNum
			puts "To high try again."
		elsif a.to_i < $randomNum
			puts "To low try again."
		elsif a.to_i == $randomNum
			puts "Congratulations, you have guess the correct number of #{a}"
			return true
		else
			return false
		end
	end
   return false
end

guess_game()







