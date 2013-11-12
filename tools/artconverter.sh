#! /bin/bash

for f in art/power*.png
	do convert $f -resize 64x64 "res/drawable-ldpi/`echo $f | sed 's@^art/@@' -`"
	convert  $f -resize 96x96 "res/drawable-mdpi/`echo $f | sed 's@^art/@@' -`"
	convert  $f -resize 128x128 "res/drawable-hdpi/`echo $f | sed 's@^art/@@' -`"
	convert  $f -resize 184x184 "res/drawable-xhdpi/`echo $f | sed 's@^art/@@' -`"
done
