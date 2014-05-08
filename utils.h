#pragma once

#ifndef WIN32
	#include <unistd.h>
#endif

#ifdef WIN32
	#define SNPRINTF _snprintf_s
#else
	#define SNPRINTF snprintf
#endif

#define ARRAY_SIZE_IN_ELEMENTS(a) (sizeof(a) / sizeof(a[0]))
#define INVALID_VALUE 0xFFFFFFFF

namespace Utils {
	void Sleep(int milliseconds);
};