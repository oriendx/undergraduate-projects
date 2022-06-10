close all
clear all
clc

%%
f = 50; %Hz
T_s = 1e-3; %s
F_s = 1/T_s;  %Hz
t = 0:T_s:2;
x_n = cos(2*pi*f*t);
X_f = fft(x_n);
plot_x = linspace(0,F_s,length(t));
plot_x_shift = linspace(-F_s/2,F_s/2,length(t));
% plot(plot_x,abs(X_f))
plot(plot_x_shift,abs(fftshift(X_f)))

%%
%getspecturm
function [freq, amp, phase] = getSpectrum(f, FS)
% freq (output) : the x-axis value of the frequency 
% amp (output)  : the amplitude spectrum 
% phase (output): the phase spectrum
% f (input)     : the vector containing the fft values
% FS (inout)    : sampling frequency

figure
freq = (linspace(-FS/2 , FS/2, length(f)));
amp = abs(fftshift(f));
%plot(freq , amp); title('The Amplitude Spectrum')

figure
phase = angle(fftshift(f));
%plot(freq , phase); title('The Phase Spectrum')

return 

%%
squarewave
function [x, T_s_vct] = square_wave_fun(T, A, T_s, W_b, W_e)
% T_s_vct (output) is a vector representing sampling time
% x (output) is a vector containing the sampled x(t) signal
% T is the signal period
% A is the signal amplitude
% T_s is sampling period
% W_b is the starting time of the observation window
% W_e is the ending time of the observation window 
%

T_s_vct = [W_b : T_s : W_e];

x_T = -1 * ones(1, length(T_s_vct));

for ii = floor(W_b/T) : ceil(W_e/T)
    x_T(( -T/4 + ii* T < T_s_vct ) & ( T_s_vct < T/4 + ii* T ) ) = 1; 
end


x = x_T;
return

%%
function [pfs_X, T_s_vct] = square_wave_PFS_fun(N, T, A, T_s, W_b, W_e)
% T_s_vct (output) is a vector representing sampling time
% pfs_X (output) is a vector containing the sampled x(t) signal
% N is the number of harmonics
% T is the signal period
% A is the signal amplitude
% T_s is sampling period
% W_b is the starting time of the observation window
% W_e is the ending time of the observation window 

Tau = 0;

T_s_vct = [W_b : T_s : W_e];
cx
p_sum = 0;
for nn = -N : 1 : N
    if mod(abs(nn), 2) 
        c_n = (-1)^((nn-1)/2)* 2/(nn*pi);
    else
        c_n = 0;
    end
    h_n = c_n * exp(-i*2*pi* nn * (T_s_vct-Tau)/T);
    p_sum = p_sum + h_n;
    
end

pfs_X = p_sum;
return

nn = -N : 1 : N;
c_n = mod(abs(nn), 2) .* (-1).^((nn-1)/2)* 2./(nn*pi);
stem(nn*1/T, abs(c_n), 'r')
    
%%
function [x, T_s_vct] = triangular_wave_fun(T, A, T_s, W_b, W_e)
% T_s_vct (output) is a vector representing sampling time
% x (output) is a vector containing the sampled x(t) signal
% T is the signal period
% A is the signal amplitude
% T_s is sampling period
% W_b is the starting time of the observation window
% W_e is the ending time of the observation window 
%

xL = linspace(-A, A, T/T_s + 1); 
xL = xL(2:end);

T_s_vctL = linspace(-T/2, 0, T/T_s + 1); 
T_s_vctL = T_s_vctL(2:end);

xR = linspace(A, -A, T/T_s + 1);
xR = xR(2:end);
x = [xL xR];

T_s_vctR = linspace(0, T/2, T/T_s + 1); 
T_s_vctR = T_s_vctR(2:end);

T_s_vct = [T_s_vctL T_s_vctR];

for ii = -1 : -1 : floor(W_b/T)
    x = [[xL xR] x ];
    T_s_vct = [[T_s_vctL T_s_vctR]+T*ii T_s_vct]; 
end

for ii = 1 : floor(W_e/T)
    x = [x [xL xR] ];
    T_s_vct = [ T_s_vct  [T_s_vctL T_s_vctR]+T*ii]; 
end

ind = ( (W_b <= T_s_vct) & (T_s_vct <= W_e) );
T_s_vct = T_s_vct(ind);
x = x(ind);

return

