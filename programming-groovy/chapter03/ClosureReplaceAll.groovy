String data = "東京スカイツリーは634m、東京タワーは333m"
result = data.replaceAll(/(\d+)m/) { g0, g1 ->
    (g1 as double) * 3.281 + "フィート"
}
assert result == "東京スカイツリーは2080.154フィート、東京タワーは1092.573フィート"
