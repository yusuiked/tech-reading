String data = "東京スカイツリーは634m、東京タワーは333m"
result = data.replaceAll(/(\d+)m/, '$1メートル')
assert result == "東京スカイツリーは634メートル、東京タワーは333メートル"
