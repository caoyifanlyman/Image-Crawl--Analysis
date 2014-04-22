#hadoop jar 18645-proj3-0.1-latest.jar -program hashtagsim -input data/tweets1m/tweets1m.txt -output data/hashtag1m/ -tmpdir tmp
#hadoop jar 18645-term-project-0.1-latest.jar -program image -input photo_url/photo_url_0.txt -output /tmp/image -tmpDir /tmp
hadoop jar 18645-term-project-0.1-latest.jar -program image -input /tmp/photo_url_min.txt -output /tmp/image -tmpDir /tmp
