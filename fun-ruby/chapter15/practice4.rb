def str2hash(string)
  hash = Hash.new()
  array = string.split(/\s+/)
  while key = array.shift
    value = array.shift
    hash[key] = value
  end
  return hash
end

p str2hash("blue 青 white 白\nred 赤")
