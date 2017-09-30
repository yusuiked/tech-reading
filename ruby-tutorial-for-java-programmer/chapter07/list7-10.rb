require 'book.jar'
require 'book-helper.rb'

include Java
include BookHelper
import 'jp.ascii.Book'

dump Book.new
