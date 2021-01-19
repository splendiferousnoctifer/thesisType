#include <conio.h>
#include <iostream>
#include <time.h>
#include <windows.h>
using namespace std;

#define S 115
#define R 114
#define FIVE 53
#define Z 122
#define SPACE 32
int input;
time_t creationtime;
time_t now;

int keycounter();

int main()
{
	while(input != 27) cout << keycounter();

	return 0;
}


int keycounter() {
	int counter = 0;

	while (1) {
		if (counter >= 5 || now > creationtime + 2) { return counter; }
		creationtime = time(0);
		input = _getch();
		counter++;
		//cout << counter;
		now = time(0);
	}

	return counter;
}