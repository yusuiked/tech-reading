class Complex {
    double re
    double im

    Complex(re, im) { this.re = re; this.im = im }
    Complex plus(c) { new Complex(re+c.re, im+c.im) }
    Complex minux(c) { new Complex(re-c.re, im-c.im) }
    Complex multiply(c) { new Complex(re*c.re - im*c.im, re*c.im + im*c.re) }
    Complex div(c) {
        def denom = (c.re ** 2) + (c.im ** 2)
        new Complex((re*c.re+im*c.im)/denom, (im*c.re-re*c.im)/denom)
    }
    String toString() { re+(im>=0?'+':'')+im+'i' }
    boolean equals(Complex c) { re == c.re && im == c.im }
}

a = new Complex(3, 1)
b = new Complex(4, -2)
assert a+b == new Complex(7, -1)
assert a*b == new Complex(14, -2)

c = new Complex(1, 1)
c += a  // +を定義すると，+=が使えるようになる
assert c == new Complex(4, 2)
