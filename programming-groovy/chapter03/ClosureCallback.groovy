ServerSocket serverSoc = new ServerSocket(8888)
while (true) {
    serverSoc.accept { soc ->
        // soc に対する処理。accept 時にクロージャがコールバックされる
    }
}
