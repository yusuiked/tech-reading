def sum_array(nums1, nums2)
  result = Array.new
  nums1.zip(nums2) do |a, b|
    result << a + b
  end
  result
end

p sum_array([1, 2, 3], [4, 6, 8])
