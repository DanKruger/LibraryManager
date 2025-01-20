~/.jdks/corretto-21.0.5/bin/jpackage \
	--name LibraryManager \
	--input ./target/ \
	--dest ./target/ \
	--main-jar LibraryManager-1.0-SNAPSHOT.jar \
    --install-dir /usr/local/bin/ \
	--main-class com.library.Main \
	--type rpm \
	# --linux-post-install-script "ln -sf /opt/librarymanager/bin/LibraryManager /usr/bin/LibraryManager" \ 
