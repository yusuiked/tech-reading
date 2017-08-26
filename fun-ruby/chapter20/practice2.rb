def ls_t(path)
  entries = Dir.entries(path)
  entries.reject!{|name| /^\./ =~ name }

  mtimes = Hash.new
  entries = entries.sort_by do |name|
    mtimes[name] = File.mtime(File.join(path, name))
  end

  entries.each do |name|
    printf("%-40s %s\n", name, mtimes[name])
  end
rescue => ex
  puts ex.message
end

ls_t(ARGV[0] || ".")
