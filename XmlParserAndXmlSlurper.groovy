def xml =
"""\
<a>
    <b id='1'>text1</b>
    <b id='2'>text2
        <c id='11'/>
    </b>
</a>"""

def p = new XmlParser().parseText(xml)
def target = p.b[1]
println "[XmlParser]"
println "getClass(): " + target.getClass()
println "target: " + target
println "target.text(): " + target.text()
println "target.'@id': " + target.'@id'
println "target.@id: " + target.@id
println ""

println "[XmlSlurper]"
p = new XmlSlurper().parseText(xml)
target = p.b[1]
println "getClass(): " + target.getClass()
println "target: " + target
println "target.text(): " + target.text()
println "target.'@id': " + target.'@id'
println "target.@id: " + target.@id
